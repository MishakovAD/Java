package UploadFileTutorial;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.zip.GZIPOutputStream;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;

import Property.Property;

@WebServlet("/DownloadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 20MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
public class DownloadServlet extends HttpServlet {
	private static final String DIR = "dir";
	private static final String GZIP = "gzip";
	private static final String MIME = "mime";
	private static final String SET_MIME = "set-mime";
	private static final String ERROR = "error";
	private static final String SIZE = "size";
	private static final String DISPOSITION = "disposition";
	private static final String EXPIRES = "expires";
	private ServletContext context;
	private String separator = "/";
	// private String root = ".";
	private String root = Property.getProperty("saveDir") + Property.getProperty("saveDirectory");
	private String error = null;
	private boolean disposition = true;
	private static final String VERSION = "ver. 2.2";
	private static final String CPR = "&copy; <a href=\"mailto:info@servletsuite.com\">Coldbeans</a> ";

	public DownloadServlet() {
	}

	public void init(ServletConfig paramServletConfig) throws ServletException {
		super.init(paramServletConfig);
		context = paramServletConfig.getServletContext();
		error = getInitParameter("error");
		String str = getInitParameter("disposition"); // в данном месте мы получаем параметр из web.xml методом. Но
														// такого параметра нет.
		if ((str != null) && ("false".equalsIgnoreCase(str))) {
			disposition = false;
		}
		if ((str = getInitParameter("dir")) == null) {
			str = root;
		}
		separator = System.getProperty("file.separator");
		if ((!str.endsWith(separator)) && (!str.endsWith("/"))) {
			str = str + separator;
		}
		root = str;
	}

	public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		doPost(paramHttpServletRequest, paramHttpServletResponse);
	}

	public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
			throws ServletException, IOException {
		PrintWriter localPrintWriter = null;
		String str1 = "";
		str1 = HttpUtils.getRequestURL(paramHttpServletRequest).toString();
		//System.out.println("str1 download " + str1); /////////////// !!!!!!!!!!!!!!
		int i;
		if ((i = str1.indexOf("?")) > 0) {
			str1 = str1.substring(0, i);
		}
		String str2;
		if ((str2 = paramHttpServletRequest.getQueryString()) == null) {
			str2 = "";
		} else {
			str2 = decode(str2);
		}
		//System.out.println("str2 download " + str2);
		//System.out.println("download root " + root);
		if (str2.length() == 0) {
			if (error != null) {
				if (error.startsWith("http://")) {
					paramHttpServletResponse.sendRedirect(paramHttpServletResponse.encodeRedirectURL(error));
				} else {
					RequestDispatcher localObject1 = getServletConfig().getServletContext().getRequestDispatcher(error);
					((RequestDispatcher) localObject1).forward(paramHttpServletRequest, paramHttpServletResponse);
				}
			} else {
				paramHttpServletResponse.setContentType("text/html");
				localPrintWriter = paramHttpServletResponse.getWriter();
				localPrintWriter.println("<html>");
				localPrintWriter.println("<br><br><br>Could not get file name ");
				localPrintWriter
						.println("<br><br><br>&copy; <a href=\"mailto:info@servletsuite.com\">Coldbeans</a> ver. 2.2");
				localPrintWriter.println("</html>");
				localPrintWriter.flush();
				localPrintWriter.close();
			}
			return;
		}
		if ((str2.indexOf(".." + separator) >= 0) || (str2.indexOf("../") >= 0)) {
			if (error != null) {
				if (error.startsWith("http://")) {
					paramHttpServletResponse.sendRedirect(paramHttpServletResponse.encodeRedirectURL(error));
				} else {
					RequestDispatcher localObject1 = getServletConfig().getServletContext().getRequestDispatcher(error);
					((RequestDispatcher) localObject1).forward(paramHttpServletRequest, paramHttpServletResponse);
				}
			} else {
				paramHttpServletResponse.setContentType("text/html");
				localPrintWriter = paramHttpServletResponse.getWriter();
				localPrintWriter.println("<html>");
				localPrintWriter.println("<br><br><br>Could not use this file name by the security restrictions ");
				localPrintWriter
						.println("<br><br><br>&copy; <a href=\"mailto:info@servletsuite.com\">Coldbeans</a> ver. 2.2");
				localPrintWriter.println("</html>");
				localPrintWriter.flush();
				localPrintWriter.close();
			}
			return;
		}
		Object localObject1 = lookupFile(root + str2);
		Object localObject2;
		if (localObject1 == null) {
			if (error != null) {
				if (error.startsWith("http://")) {
					paramHttpServletResponse.sendRedirect(paramHttpServletResponse.encodeRedirectURL(error));
				} else {
					localObject2 = getServletConfig().getServletContext().getRequestDispatcher(error);
					((RequestDispatcher) localObject2).forward(paramHttpServletRequest, paramHttpServletResponse);
				}
			} else {
				paramHttpServletResponse.setContentType("text/html");
				localPrintWriter = paramHttpServletResponse.getWriter();
				localPrintWriter.println("<html>");
				localPrintWriter.println("<br><br><br>Could not read file " + str2);
				localPrintWriter
						.println("<br><br><br>&copy; <a href=\"mailto:info@servletsuite.com\">Coldbeans</a> ver. 2.2");
				localPrintWriter.println("</html>");
				localPrintWriter.flush();
				localPrintWriter.close();
			}
			return;
		}
		if ((!((File) localObject1).exists()) || (!((File) localObject1).canRead())) {
			if (error != null) {
				if (error.startsWith("http://")) {
					paramHttpServletResponse.sendRedirect(paramHttpServletResponse.encodeRedirectURL(error));
				} else {
					localObject2 = getServletConfig().getServletContext().getRequestDispatcher(error);
					((RequestDispatcher) localObject2).forward(paramHttpServletRequest, paramHttpServletResponse);
				}
			} else {
				paramHttpServletResponse.setContentType("text/html");
				localPrintWriter = paramHttpServletResponse.getWriter();
				localPrintWriter.println("<html><font face=\"Arial\">");
				localPrintWriter.println("<br><br><br>Could not read file " + str2);
				localPrintWriter.print("<br>Reasons are: ");
				if (!((File) localObject1).exists()) {
					localPrintWriter.println("file does not exist");
				} else {
					localPrintWriter.println("file is not readable at this moment");
				}
				localPrintWriter
						.println("<br><br><br>&copy; <a href=\"mailto:info@servletsuite.com\">Coldbeans</a> ver. 2.2");
				localPrintWriter.println("</font></html>");
				localPrintWriter.flush();
				localPrintWriter.close();
			}
			return;
		}
		String str3 = paramHttpServletRequest.getHeader("Accept-Encoding");
		System.out.println("download str3 " + str3);
		int j = 0;
		String str4 = getInitParameter("size");
		long l1 = -1L;
		String str5 = getInitParameter("expires");
		if (str5 != null) {
			long l2 = -1L;
			try {
				l2 = Long.parseLong(str5);
			} catch (Exception localException2) {
				l2 = -1L;
			}
			if (l2 > 0L) {
				Date localDate = new Date();
				paramHttpServletResponse.setDateHeader("Expires", localDate.getTime() + l2 * 1000L);
				paramHttpServletResponse.setHeader("Cache-Control", "max-age=" + l2);
			}
		}
		if (str4 != null) {
			try {
				l1 = Long.parseLong(str4);
			} catch (Exception localException1) {
				l1 = -1L;
			}
		}
		if ((str3 != null) && (str3.indexOf("gzip") >= 0) && ("true".equalsIgnoreCase(getInitParameter("gzip")))) {
			j = 1;
		}
		Object localObject3;
		if (j != 0) {
			paramHttpServletResponse.setHeader("Content-Encoding", "gzip");
			if (disposition) {
				paramHttpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + str2 + "\"");
			}
			localObject2 = paramHttpServletResponse.getOutputStream();
			localObject3 = new GZIPOutputStream((OutputStream) localObject2);
			dumpFile(root + str2, (OutputStream) localObject3, l1);
			((GZIPOutputStream) localObject3).close();
			((OutputStream) localObject2).close();
		} else {
			localObject3 = getInitParameter("set-mime");
			if (localObject3 != null) {
				paramHttpServletResponse.setContentType((String) localObject3);
			} else if ("true".equalsIgnoreCase(getInitParameter("mime"))) {
				paramHttpServletResponse.setContentType(getMimeType(str2));
			} else {
				paramHttpServletResponse.setContentType("application/octet-stream");
			}
			if (disposition) {
				paramHttpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"" + str2 + "\"");
			}
			paramHttpServletResponse.setContentLength((int) ((File) localObject1).length());
			localObject2 = paramHttpServletResponse.getOutputStream();
			dumpFile(root + str2, (OutputStream) localObject2, l1);
			((OutputStream) localObject2).flush();
			((OutputStream) localObject2).close();
		}
	}

	private String getFromQuery(String paramString1, String paramString2) {
		if (paramString1 == null) {
			return "";
		}
		int i = paramString1.indexOf(paramString2);
		if (i < 0) {
			return "";
		}
		String str = paramString1.substring(i + paramString2.length());
		if ((i = str.indexOf("&")) < 0) {
			return str;
		}
		return str.substring(0, i);
	}

	private void dumpFile(String paramString, OutputStream paramOutputStream, long paramLong) {
		String str = paramString;
		byte[] arrayOfByte = new byte['က'];
		long l = 0L;
		try {
			BufferedInputStream localBufferedInputStream = new BufferedInputStream(
					new FileInputStream(lookupFile(str)));
			int i;
			while ((i = localBufferedInputStream.read(arrayOfByte, 0, 4096)) != -1) {
				paramOutputStream.write(arrayOfByte, 0, i);
				if (paramLong > 0L) {
					l += i;
					if (l >= paramLong) {
						break;
					}
				}
			}
			localBufferedInputStream.close();
		} catch (Exception localException) {
		}
	}

	private String decode(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer(0);
		paramString = paramString.replaceAll("%20", " "); //Исправлен быг с тем, что пробелы не заменялись нормально. Возможно это костыль.
		for (int j = 0; j < paramString.length(); j++) {
			char c = paramString.charAt(j);
			if (c == '+') {
				localStringBuffer.append(' ');
			} else if (c == '%') {
				int i = 0;
				for (int k = 0; k < 2; k++) {
					i = (char) (i * 16);
					c = paramString.charAt(++j);
					if ((c >= '0') && (c <= '9')) {
						i = (char) (i + (c - '0'));
					} else {
						if (((c < 'A') || (c > 'F')) && ((c < 'a') || (c > 'f'))) {
							break;
						}
						switch (c) {
						case 'A':
						case 'a':
							i = (char) (i + 10);
							break;
						case 'B':
						case 'b':
							i = (char) (i + 11);
							break;
						case 'C':
						case 'c':
							i = (char) (i + 12);
							break;
						case 'D':
						case 'd':
							i = (char) (i + 13);
							break;
						case 'E':
						case 'e':
							i = (char) (i + 14);
							break;
						case 'F':
						case 'f':
							i = (char) (i + 15);
						}
					}
				}
				localStringBuffer.append(i);
			} else {
				localStringBuffer.append(c);
			}
		}
		return localStringBuffer.toString();
	}

	public String getServletInfo() {
		return "A DownloadServlet servlet (c) Coldbeans  mailto: info@servletsuite.com";
	}

	private File lookupFile(String paramString) // Вероятно производится поиск и получение файла. paramString - путь до
												// него ТОЧНО ЕСТЬ БАГ С СИМВОЛОМ ПРОБЕЛ %20 - не декодирует. вместо
												// него символ 32 (исправлено в методе decode)
	{
		System.out.println("lookupFile " + paramString);
		File localFile = new File(paramString);
		return localFile.isAbsolute() ? localFile : new File(context.getRealPath("/"), paramString);
	}

	private String getMimeType(String paramString) {
		int i = paramString.lastIndexOf(".");
		if ((i > 0) && (i < paramString.length() - 1)) {
			String str = paramString.substring(i + 1);
			if (str.equalsIgnoreCase("ai")) {
				return "application/postscript";
			}
			if (str.equalsIgnoreCase("amr")) {
				return "audio/amr";
			}
			if (str.equalsIgnoreCase("apk")) {
				return "application/vnd.android.package-archive";
			}
			if (str.equalsIgnoreCase("aif")) {
				return "audio/x-aiff";
			}
			if (str.equalsIgnoreCase("aiff")) {
				return "audio/x-aiff";
			}
			if (str.equalsIgnoreCase("aifc")) {
				return "audio/x-aiff";
			}
			if (str.equalsIgnoreCase("avi")) {
				return "video/x-msvideo";
			}
			if (str.equalsIgnoreCase("au")) {
				return "audio/basic";
			}
			if (str.equalsIgnoreCase("bcpio")) {
				return "application/x-bcpio";
			}
			if (str.equalsIgnoreCase("cpio")) {
				return "application/x-cpio";
			}
			if (str.equalsIgnoreCase("csh")) {
				return "application/x-csh";
			}
			if (str.equalsIgnoreCase("cpt")) {
				return "application/mac-compactpro";
			}
			if (str.equalsIgnoreCase("cgi")) {
				return "application/x-httpd-cgi";
			}
			if (str.equalsIgnoreCase("cdf")) {
				return "application/x-netcdf";
			}
			if (str.equalsIgnoreCase("cat")) {
				return "text/xml";
			}
			if (str.equalsIgnoreCase("css")) {
				return "text/css";
			}
			if (str.equalsIgnoreCase("doc")) {
				return "application/msword";
			}
			if (str.equalsIgnoreCase("dcr")) {
				return "application/x-director";
			}
			if (str.equalsIgnoreCase("dir")) {
				return "application/x-director";
			}
			if (str.equalsIgnoreCase("dxr")) {
				return "application/x-director";
			}
			if (str.equalsIgnoreCase("dvi")) {
				return "application/x-dvi";
			}
			if (str.equalsIgnoreCase("dtd")) {
				return "text/dtd";
			}
			if (str.equalsIgnoreCase("eps")) {
				return "application/postscript";
			}
			if (str.equalsIgnoreCase("etx")) {
				return "text/x-setext";
			}
			if (str.equalsIgnoreCase("ent")) {
				return "text/xml";
			}
			if (str.equalsIgnoreCase("ief")) {
				return "image/ief";
			}
			if (str.equalsIgnoreCase("ice")) {
				return "x-conference/x-cooltalk";
			}
			if (str.equalsIgnoreCase("gif")) {
				return "image/gif";
			}
			if (str.equalsIgnoreCase("gtar")) {
				return "application/x-gtar";
			}
			if (str.equalsIgnoreCase("gz")) {
				return "application/x-gzip";
			}
			if (str.equalsIgnoreCase("hdf")) {
				return "application/x-hdf";
			}
			if (str.equalsIgnoreCase("jad")) {
				return "text/vnd.sun.j2me.app-descriptor";
			}
			if (str.equalsIgnoreCase("jar")) {
				return "application/java-archive";
			}
			if (str.equalsIgnoreCase("jpeg")) {
				return "image/jpeg";
			}
			if (str.equalsIgnoreCase("jpg")) {
				return "image/jpeg";
			}
			if (str.equalsIgnoreCase("jpe")) {
				return "image/jpeg";
			}
			if (str.equalsIgnoreCase("jar")) {
				return "application/java-archive";
			}
			if (str.equalsIgnoreCase("jnlp")) {
				return "application/x-java-jnlp-file";
			}
			if (str.equalsIgnoreCase("jsp")) {
				return "application/jsp";
			}
			if (str.equalsIgnoreCase("latex")) {
				return "application/x-latex";
			}
			if (str.equalsIgnoreCase("mif")) {
				return "application/x-mif";
			}
			if (str.equalsIgnoreCase("mid")) {
				return "audio/midi";
			}
			if (str.equalsIgnoreCase("mmf")) {
				return "application/vnd.smaf";
			}
			if (str.equalsIgnoreCase("man")) {
				return "application/x-troff-man";
			}
			if (str.equalsIgnoreCase("me")) {
				return "application/x-troff-me";
			}
			if (str.equalsIgnoreCase("ms")) {
				return "application/x-troff-ms";
			}
			if (str.equalsIgnoreCase("nc")) {
				return "application/x-netcdf";
			}
			if (str.equalsIgnoreCase("hqx")) {
				return "application/mac-binhex40";
			}
			if (str.equalsIgnoreCase("html")) {
				return "text/html";
			}
			if (str.equalsIgnoreCase("htm")) {
				return "text/html";
			}
			if (str.equalsIgnoreCase("oda")) {
				return "application/oda";
			}
			if (str.equalsIgnoreCase("pdf")) {
				return "application/pdf";
			}
			if (str.equalsIgnoreCase("ps")) {
				return "application/postscript";
			}
			if (str.equalsIgnoreCase("ppt")) {
				return "application/powerpoint";
			}
			if (str.equalsIgnoreCase("pdb")) {
				return "chemical/x-pdb";
			}
			if (str.equalsIgnoreCase("png")) {
				return "image/png";
			}
			if (str.equalsIgnoreCase("pnm")) {
				return "image/x-portable-anymap";
			}
			if (str.equalsIgnoreCase("pbm")) {
				return "image/x-portable-bitmap";
			}
			if (str.equalsIgnoreCase("pgm")) {
				return "image/x-portable-graymap";
			}
			if (str.equalsIgnoreCase("ppm")) {
				return "image/x-portable-pixmap";
			}
			if (str.equalsIgnoreCase("qcp")) {
				return "audio/vnd.qcelp";
			}
			if (str.equalsIgnoreCase("qt")) {
				return "video/quicktime";
			}
			if (str.equalsIgnoreCase("rtf")) {
				return "application/rtf";
			}
			if (str.equalsIgnoreCase("roff")) {
				return "application/x-troff";
			}
			if (str.equalsIgnoreCase("ram")) {
				return "audio/x-pn-realaudio";
			}
			if (str.equalsIgnoreCase("rpm")) {
				return "audio/x-pn-realaudio-plugin";
			}
			if (str.equalsIgnoreCase("ras")) {
				return "image/x-cmu-raster";
			}
			if (str.equalsIgnoreCase("ra")) {
				return "audio/x-realaudio";
			}
			if (str.equalsIgnoreCase("rgb")) {
				return "image/x-rgb";
			}
			if (str.equalsIgnoreCase("rtx")) {
				return "text/richtext";
			}
			if (str.equalsIgnoreCase("vcd")) {
				return "application/x-cdlink";
			}
			if (str.equalsIgnoreCase("vrml")) {
				return "x-world/x-vrml";
			}
			if (str.equalsIgnoreCase("Z")) {
				return "application/x-compress";
			}
			if (str.equalsIgnoreCase("skp")) {
				return "application/x-koan";
			}
			if (str.equalsIgnoreCase("skd")) {
				return "application/x-koan";
			}
			if (str.equalsIgnoreCase("skt")) {
				return "application/x-koan";
			}
			if (str.equalsIgnoreCase("skm")) {
				return "application/x-koan";
			}
			if (str.equalsIgnoreCase("sh")) {
				return "application/x-sh";
			}
			if (str.equalsIgnoreCase("shar")) {
				return "application/x-shar";
			}
			if (str.equalsIgnoreCase("sit")) {
				return "application/x-stuffit";
			}
			if (str.equalsIgnoreCase("sv4cpio")) {
				return "application/x-sv4cpio";
			}
			if (str.equalsIgnoreCase("sv4crc")) {
				return "application/x-sv4crc";
			}
			if (str.equalsIgnoreCase("snd")) {
				return "audio/basic";
			}
			if (str.equalsIgnoreCase("sgml")) {
				return "text/x-sgml";
			}
			if (str.equalsIgnoreCase("sgm")) {
				return "text/x-sgml";
			}
			if (str.equalsIgnoreCase("src")) {
				return "application/x-wais-source";
			}
			if (str.equalsIgnoreCase("sty")) {
				return "text/xml";
			}
			if (str.equalsIgnoreCase("tar")) {
				return "application/x-tar";
			}
			if (str.equalsIgnoreCase("tcl")) {
				return "application/x-tcl";
			}
			if (str.equalsIgnoreCase("tex")) {
				return "application/x-tex";
			}
			if (str.equalsIgnoreCase("textinfo")) {
				return "application/x-texinfo";
			}
			if (str.equalsIgnoreCase("texi")) {
				return "application/x-texinfo";
			}
			if (str.equalsIgnoreCase("t")) {
				return "application/x-troff";
			}
			if (str.equalsIgnoreCase("tr")) {
				return "application/x-troff";
			}
			if (str.equalsIgnoreCase("tiff")) {
				return "image/tiff";
			}
			if (str.equalsIgnoreCase("tif")) {
				return "image/tiff";
			}
			if (str.equalsIgnoreCase("txt")) {
				return "text/plain";
			}
			if (str.equalsIgnoreCase("tsv")) {
				return "text/tab-separated-values";
			}
			if (str.equalsIgnoreCase("ustar")) {
				return "application/x-ustar";
			}
			if (str.equalsIgnoreCase("mpga")) {
				return "audio/mpeg";
			}
			if (str.equalsIgnoreCase("mp2")) {
				return "audio/mpeg";
			}
			if (str.equalsIgnoreCase("mp3")) {
				return "audio/mpeg";
			}
			if (str.equalsIgnoreCase("mpeg")) {
				return "video/mpeg";
			}
			if (str.equalsIgnoreCase("mpg")) {
				return "video/mpeg";
			}
			if (str.equalsIgnoreCase("mpe")) {
				return "video/mpeg";
			}
			if (str.equalsIgnoreCase("mov")) {
				return "video/quicktime";
			}
			if (str.equalsIgnoreCase("movie")) {
				return "video/x-sgi-movie";
			}
			if (str.equalsIgnoreCase("mp4")) {
				return "video/3gpp";
			}
			if (str.equalsIgnoreCase("xaml")) {
				return "application/xaml+xml";
			}
			if (str.equalsIgnoreCase("xbm")) {
				return "image/x-xbitmap";
			}
			if (str.equalsIgnoreCase("xpm")) {
				return "image/x-xpixmap";
			}
			if (str.equalsIgnoreCase("xwd")) {
				return "image/x-xwindowdump";
			}
			if (str.equalsIgnoreCase("xml")) {
				return "text/xml";
			}
			if (str.equalsIgnoreCase("xsl")) {
				return "text/xsl";
			}
			if (str.equalsIgnoreCase("xyz")) {
				return "chemical/x-pdb";
			}
			if (str.equalsIgnoreCase("zip")) {
				return "application/zip";
			}
			if (str.equalsIgnoreCase("wav")) {
				return "audio/x-wav";
			}
			if (str.equalsIgnoreCase("wrl")) {
				return "x-world/x-vrml";
			}
			if (str.equalsIgnoreCase("war")) {
				return "application/java-archive";
			}
			if (str.equalsIgnoreCase("wml")) {
				return "text/vnd.wap.wml";
			}
			if (str.equalsIgnoreCase("wmlc")) {
				return "application/vnd.wap.wmlc";
			}
			if (str.equalsIgnoreCase("wmls")) {
				return "text/vnd.wap.wmlscript";
			}
			if (str.equalsIgnoreCase("wmlsc")) {
				return "application/vnd.wap.wmlscriptc";
			}
			if (str.equalsIgnoreCase("wbmp")) {
				return "image/vnd.wap.wbmp";
			}
			if (str.equalsIgnoreCase("3gp")) {
				return "video/3gp";
			}
			if (str.equalsIgnoreCase("3g2")) {
				return "video/3gpp2";
			}
		}
		return "application/octet-stream";
	}
}

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE >
<html>
<head>
<title>Upload files</title>
</head>
<body>
 

    <h2>Upload Files</h2>
 
    <form method="post" action="/niikp/uploadFile" enctype="multipart/form-data"/>
        <br>
        <input type="file" name="file"  />
        <br>
        <input type="file" name="file" />
        <br>
        Description:
        <br>
        <input type="text" name="description" size="100" />
        <br />
        <br />
        <input type="submit" value="Upload" />
    </form>
    
</body>
</html>
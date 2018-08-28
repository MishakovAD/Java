package OutgoingMail;

import java.util.ArrayList;

import IncomingMail.IncomingMail;
import UserProfile.UserProfile;

public class OutgoingMail {
	private int idMail; //regNum
	private String regDate;
	private String mailNum;
	private String destination; //adresat
	private int forWhomId; //ispolnitel'
	private String sendDate;
	private String mailTheme;
	private int executorId; //ispolnitel'
	private int realExecutorId; //real ispolnitel'
	private String incomingMailNum;
	private String toWhomItIsPainted;
	private int incomingMailId;
	private String note;
	private String mailingNote; //primechanie rassbIlki
	private String filePathAndName;
	private boolean isDeleted = false;
	
	public static ArrayList<String> destinationList = new ArrayList<>();
	
	public static ArrayList<String> getDestinationList() {
		destinationList.add("АНО «АКНИИПО»");
		destinationList.add("ВНИИА");
		destinationList.add("ГНП РКЦ «ЦСКБ-Прогресс»");
		destinationList.add("ГНЦ ФГУП \"Центр Келдыша\"");
		destinationList.add("ЗАО \"ПЭК\"");
		destinationList.add("ЗАО «Восток»");
		destinationList.add("Институт космических исследований РАН");
		destinationList.add("Конструкторское Бюро \"Салют\" им. М.В. Хруничева");
		destinationList.add("НИИ КС - филиал ФГУП \"ГКНПЦ им. М.В. Хруничева\"");
		destinationList.add("НИИСИ РАН");
		destinationList.add("НИИЯФ МГУ");
		destinationList.add("НПП \"ОПТЭКС\"");
		destinationList.add("НТЦ \"Космонит\"");
		destinationList.add("ОАО \"Ижевский радиозавод\"");
		destinationList.add("ОАО \"Интеграл\"");
		destinationList.add("ОАО \"Композит\"");
		destinationList.add("ОАО \"Машиностроительный завод \"Арсенал\"");
		destinationList.add("ОАО \"Научно-исследовательский институт космического приборостроения\"");
		destinationList.add("ОАО \"Научно-производственная организация \"Орион\"");
		destinationList.add("ОАО \"НИИ \" Субмикрон\"");
		destinationList.add("ОАО \"НИИ ТП\"");
		destinationList.add("ОАО \"НИИФИ\"");
		destinationList.add("ОАО \"НПК СПП\"");
		destinationList.add("ОАО \"НПО ИТ\"");
		destinationList.add("ОАО \"НПЦ \"Полюс\"");
		destinationList.add("ОАО \"ОКБ МЭИ\"");
		destinationList.add("ОАО \"Ракетно-космическая корпорация \"Энергия\" имени С.П. Королева\"");
		destinationList.add("ОАО \"Российские космические системы\"");
		destinationList.add("ОАО \"Сатурн\"");
		destinationList.add("ОАО «АВЭКС»");
		destinationList.add("ОАО «Воронежский Завод Полупроводниковых Приборов-Сборка» ");
		destinationList.add("ОАО «ВПК «НПО машиностроения»");
		destinationList.add("ОАО «ИТЦ–НПО ПМ»");
		destinationList.add("ОАО «НИИЭМ»");
		destinationList.add("ОАО «НПП «КВАНТ»");
		destinationList.add("ОАО «РНИИ «ЭЛЕКТРОНСТАНДАРТ»");
		destinationList.add("ОАО «ЦНИИ «ЦИКЛОН»");
		destinationList.add("ОАО «ЭНПО СПЭЛС»");
		destinationList.add("ОАО ИПК «Машприбор»");
		destinationList.add("ОАО ИСС им. академика М.Ф. Решетнева");
		destinationList.add("ОИЯИ");
		destinationList.add("ОКБ «ФАКЕЛ»");
		destinationList.add("ООО «ИРЗ ТЕСТ»");
		destinationList.add("ПО «Полет» – филиал ФГУП «ГКНПЦ им. М.В.Хруничева» ");
		destinationList.add("Росатом");
		destinationList.add("Роскосмос");
		destinationList.add("ФГУП \"Научно-исследовательский институт командных приборов\"");
		destinationList.add("ФГУП \"НПП ВНИИЭМ с заводом им. А.Г.Иосифьяна\" ");
		destinationList.add("ФГУП \"Опытное конструкторское бюро \"Факел\"");
		destinationList.add("ФГУП \"Организация \"Агат\"");
		destinationList.add("ФГУП «Воронежский механический завод» (ВМЗ) филиал ФГУП «ГКНПЦ им. М.В. Хруничева»");
		destinationList.add("ФГУП «ГКНПЦ  им. М.В. Хруничева»");
		destinationList.add("ФГУП «Главкосмос»");
		destinationList.add("ФГУП «Звезда»");
		destinationList.add("ФГУП «Конструкторское бюро «Арсенал» им. М.В. Фрунзе»");
		destinationList.add("ФГУП «Московское опытно-конструкторское бюро «Марс»");
		destinationList.add("ФГУП «Научно производственное объединение им. С. А. Лавочкина»");
		destinationList.add("ФГУП «Научно-производственное объединение «Техномаш»");
		destinationList.add("ФГУП «НИИМаш»");
		destinationList.add("ФГУП «НИИП»");
		destinationList.add("ФГУП «ПНИЭИ»");
		destinationList.add("ФГУП «РФЯЦ-ВНИИТФ»");
		destinationList.add("ФГУП «РФЯЦ-ВНИИЭФ»");
		destinationList.add("ФГУП «ЦНИИ «Комета»");
		destinationList.add("ФГУП «ЦНИРТИ им. академика А.И. Берга»");
		destinationList.add("ФГУП НИИР");
		destinationList.add("ФГУП НПП Пульсар");
		destinationList.add("ФГУП НПЦ автоматики и приборостроения имени академика Н.А.Пилюгина");
		destinationList.add("Филиал ФБУ «46 ЦНИИ Министерства Обороны России»");
		destinationList.add("ФКП \"Научно-испытательный центр ракетно-космической промышленности\"");
		destinationList.add("ФЛ ФГУП «ГКНПЦ  им. М.В. Хруничева» - «НИИ КС им. А.А. Максимова»");
		destinationList.add("ЦНИИМАШ");
		destinationList.add("ЦЭНКИ");
		return destinationList;
	}
	
	public OutgoingMail() {
		super();
	}

	public int getIdMail() {
		return idMail;
	}

	public void setIdMail(int idMail) {
		this.idMail = idMail;
	}

	public int getForWhom() {
		return forWhomId;
	}

	public void setForWhom(int forWhom) {
		this.forWhomId = forWhom;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getMailNum() {
		return mailNum;
	}

	public void setMailNum(String mailNum) {
		this.mailNum = "№ Ф-ВА-01/" + mailNum;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getMailTheme() {
		return mailTheme;
	}

	public void setMailTheme(String mailTheme) {
		this.mailTheme = mailTheme;
	}

	public int getExecutor() {
		return executorId;
	}

	public void setExecutor(int executor) {
		this.executorId = executor;
	}

	public int getRealExecutor() {
		return realExecutorId;
	}

	public void setRealExecutor(int realExecutor) {
		this.realExecutorId = realExecutor;
	}

	public String getIncomingMailNum() {
		return incomingMailNum;
	}

	public void setIncomingMailNum(String incomingMailNum) {
		this.incomingMailNum = incomingMailNum;
	}

	public String getToWhomItIsPainted() {
		return toWhomItIsPainted;
	}

	public void setToWhomItIsPainted(String toWhomItIsPainted) {
		this.toWhomItIsPainted = toWhomItIsPainted;
	}

	public int getIncomingMailId() {
		return incomingMailId;
	}

	public void setIncomingMailId(int incomingMailId) {
		this.incomingMailId = incomingMailId;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMailingNote() {
		return mailingNote;
	}

	public void setMailingNote(String mailingNote) {
		this.mailingNote = mailingNote;
	}
	
	public String getFilePathAndName() {
		return filePathAndName;
	}

	public void setFilePathAndName(String filePathAndName) {
		this.filePathAndName = filePathAndName;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public String toString() {
		return "OutgoingMail [idMail=" + idMail + ", regDate=" + regDate + ", mailNum=" + mailNum + ", destination="
				+ destination + ", forWhomId=" + forWhomId + ", sendDate=" + sendDate + ", mailTheme=" + mailTheme
				+ ", executorId=" + executorId + ", realExecutorId=" + realExecutorId + ", incomingMailNum="
				+ incomingMailNum + ", toWhomItIsPainted=" + toWhomItIsPainted + ", note=" + note + ", mailingNote="
				+ mailingNote + ", filePathAndName=" + filePathAndName + "]";
	}


	
	

}

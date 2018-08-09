package IncomingMail;

import java.util.ArrayList;
import java.util.Date;
import Work.Work;

public class IncomingMail {
	/*
	 * add user id, who add mail if it need */
	private String regDate;
	private int idMail; //regNum
	private String typeMail;
	private String sender;
	private String sendDate;
	private String mailNum;
	private String mailTheme;
	private String secondFloorDate;
	private String secondFloorNum;
	private String filePathAndName;
	private Work resolution;
	
	public static ArrayList<String> typeMailList = new ArrayList<>(); // заполнить необходимыми типами письма
	public static ArrayList<String> senderList = new ArrayList<>(); // заполнить необходимыми отправителями
	
	public static ArrayList<String> getTypeMailList() {
		typeMailList.add("Поручение");
		typeMailList.add("Документ");
		typeMailList.add("Письмо");
		typeMailList.add("Выписка из распоряжения");
		typeMailList.add("Служебная записка");
		typeMailList.add("Распоряжение");
		typeMailList.add("Информационное сообщение");
		typeMailList.add("Приказ");
		return typeMailList;
	}
	
	public static ArrayList<String> getSenderList() {
		senderList.add("АНО «АКНИИПО»");
		senderList.add("ВНИИА");
		senderList.add("ГНП РКЦ «ЦСКБ-Прогресс»");
		senderList.add("ГНЦ ФГУП \"Центр Келдыша\"");
		senderList.add("ЗАО \"ПЭК\"");
		senderList.add("ЗАО «Восток»");
		senderList.add("Институт космических исследований РАН");
		senderList.add("Конструкторское Бюро \"Салют\" им. М.В. Хруничева");
		senderList.add("НИИ КС - филиал ФГУП \"ГКНПЦ им. М.В. Хруничева\"");
		senderList.add("НИИСИ РАН");
		senderList.add("НИИЯФ МГУ");
		senderList.add("НПП \"ОПТЭКС\"");
		senderList.add("НТЦ \"Космонит\"");
		senderList.add("ОАО \"Ижевский радиозавод\"");
		senderList.add("ОАО \"Интеграл\"");
		senderList.add("ОАО \"Композит\"");
		senderList.add("ОАО \"Машиностроительный завод \"Арсенал\"");
		senderList.add("ОАО \"Научно-исследовательский институт космического приборостроения\"");
		senderList.add("ОАО \"Научно-производственная организация \"Орион\"");
		senderList.add("ОАО \"НИИ \" Субмикрон\"");
		senderList.add("ОАО \"НИИ ТП\"");
		senderList.add("ОАО \"НИИФИ\"");
		senderList.add("ОАО \"НПК СПП\"");
		senderList.add("ОАО \"НПО ИТ\"");
		senderList.add("ОАО \"НПЦ \"Полюс\"");
		senderList.add("ОАО \"ОКБ МЭИ\"");
		senderList.add("ОАО \"Ракетно-космическая корпорация \"Энергия\" имени С.П. Королева\"");
		senderList.add("ОАО \"Российские космические системы\"");
		senderList.add("ОАО \"Сатурн\"");
		senderList.add("ОАО «АВЭКС»");
		senderList.add("ОАО «Воронежский Завод Полупроводниковых Приборов-Сборка» ");
		senderList.add("ОАО «ВПК «НПО машиностроения»");
		senderList.add("ОАО «ИТЦ–НПО ПМ»");
		senderList.add("ОАО «НИИЭМ»");
		senderList.add("ОАО «НПП «КВАНТ»");
		senderList.add("ОАО «РНИИ «ЭЛЕКТРОНСТАНДАРТ»");
		senderList.add("ОАО «ЦНИИ «ЦИКЛОН»");
		senderList.add("ОАО «ЭНПО СПЭЛС»");
		senderList.add("ОАО ИПК «Машприбор»");
		senderList.add("ОАО ИСС им. академика М.Ф. Решетнева");
		senderList.add("ОИЯИ");
		senderList.add("ОКБ «ФАКЕЛ»");
		senderList.add("ООО «ИРЗ ТЕСТ»");
		senderList.add("ПО «Полет» – филиал ФГУП «ГКНПЦ им. М.В.Хруничева» ");
		senderList.add("Росатом");
		senderList.add("Роскосмос");
		senderList.add("ФГУП \"Научно-исследовательский институт командных приборов\"");
		senderList.add("ФГУП \"НПП ВНИИЭМ с заводом им. А.Г.Иосифьяна\" ");
		senderList.add("ФГУП \"Опытное конструкторское бюро \"Факел\"");
		senderList.add("ФГУП \"Организация \"Агат\"");
		senderList.add("ФГУП «Воронежский механический завод» (ВМЗ) филиал ФГУП «ГКНПЦ им. М.В. Хруничева»");
		senderList.add("ФГУП «ГКНПЦ  им. М.В. Хруничева»");
		senderList.add("ФГУП «Главкосмос»");
		senderList.add("ФГУП «Звезда»");
		senderList.add("ФГУП «Конструкторское бюро «Арсенал» им. М.В. Фрунзе»");
		senderList.add("ФГУП «Московское опытно-конструкторское бюро «Марс»");
		senderList.add("ФГУП «Научно производственное объединение им. С. А. Лавочкина»");
		senderList.add("ФГУП «Научно-производственное объединение «Техномаш»");
		senderList.add("ФГУП «НИИМаш»");
		senderList.add("ФГУП «НИИП»");
		senderList.add("ФГУП «ПНИЭИ»");
		senderList.add("ФГУП «РФЯЦ-ВНИИТФ»");
		senderList.add("ФГУП «РФЯЦ-ВНИИЭФ»");
		senderList.add("ФГУП «ЦНИИ «Комета»");
		senderList.add("ФГУП «ЦНИРТИ им. академика А.И. Берга»");
		senderList.add("ФГУП НИИР");
		senderList.add("ФГУП НПП Пульсар");
		senderList.add("ФГУП НПЦ автоматики и приборостроения имени академика Н.А.Пилюгина");
		senderList.add("Филиал ФБУ «46 ЦНИИ Министерства Обороны России»");
		senderList.add("ФКП \"Научно-испытательный центр ракетно-космической промышленности\"");
		senderList.add("ФЛ ФГУП «ГКНПЦ  им. М.В. Хруничева» - «НИИ КС им. А.А. Максимова»");
		senderList.add("ЦНИИМАШ");
		senderList.add("ЦЭНКИ");
		return senderList;
	}
	
	public IncomingMail() {
		super();
	}

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum, String mailTheme,
			String secondFloorDate) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
	}

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum, String mailTheme,
			String secondFloorDate, Work resolution) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.resolution = resolution;
	}

	public IncomingMail(String regDate, int idMail, String typeMail, String sender, String sendDate, String mailNum,
			String mailTheme, String secondFloorDate, Work resolution) {
		super();
		this.regDate = regDate;
		this.idMail = idMail;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.resolution = resolution;
	}
	

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum, String mailTheme,
			String secondFloorDate, String filePathAndName, Work resolution) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.filePathAndName = filePathAndName;
		this.resolution = resolution;
	}

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum, String mailTheme,
			String secondFloorDate, String filePathAndName) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.filePathAndName = filePathAndName;
	}
	
	

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum,
			String mailTheme, String secondFloorDate, String secondFloorNum, String filePathAndName) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.secondFloorNum = secondFloorNum;
		this.filePathAndName = filePathAndName;
	}

	

	public IncomingMail(String regDate, String typeMail, String sender, String sendDate, String mailNum,
			String mailTheme, String secondFloorDate, String secondFloorNum, String filePathAndName, Work resolution) {
		super();
		this.regDate = regDate;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.secondFloorNum = secondFloorNum;
		this.filePathAndName = filePathAndName;
		this.resolution = resolution;
	}

	public IncomingMail(String regDate, int idMail, String typeMail, String sender, String sendDate, String mailNum,
			String mailTheme, String secondFloorDate, String secondFloorNum, String filePathAndName, Work resolution) {
		super();
		this.regDate = regDate;
		this.idMail = idMail;
		this.typeMail = typeMail;
		this.sender = sender;
		this.sendDate = sendDate;
		this.mailNum = mailNum;
		this.mailTheme = mailTheme;
		this.secondFloorDate = secondFloorDate;
		this.secondFloorNum = secondFloorNum;
		this.filePathAndName = filePathAndName;
		this.resolution = resolution;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate2) {
		this.regDate = regDate2;
	}

	public int getIdMail() {
		return idMail;
	}

	public void setIdMail(int idMail) {
		this.idMail = idMail;
	}

	public String getTypeMail() {
		return typeMail;
	}

	public void setTypeMail(String typeMail) {
		this.typeMail = typeMail;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate2) {
		this.sendDate = sendDate2;
	}

	public String getMailNum() {
		return mailNum;
	}

	public void setMailNum(String mailNum) {
		this.mailNum = mailNum;
	}

	public String getMailTheme() {
		return mailTheme;
	}

	public void setMailTheme(String mailTheme) {
		this.mailTheme = mailTheme;
	}

	public String getSecondFloorDate() {
		return secondFloorDate;
	}

	public void setSecondFloorDate(String secondFloorDate2) {
		this.secondFloorDate = secondFloorDate2;
	}
	
	public String getSecondFloorNum() {
		return secondFloorNum;
	}

	public void setSecondFloorNum(String secondFloorNum) {
		this.secondFloorNum = secondFloorNum;
	}

	public String getFilePathAndName() {
		return filePathAndName;
	}

	public void setFilePathAndName(String filePathAndName) {
		this.filePathAndName = filePathAndName;
	}

	public Work getResolution() {
		return resolution;
	}

	public void setResolution(Work resolution) {
		this.resolution = resolution;
	}

	@Override
	public String toString() {
		return "IncomingMail [regDate=" + regDate + ", idMail=" + idMail + ", typeMail=" + typeMail + ", sender="
				+ sender + ", sendDate=" + sendDate + ", mailNum=" + mailNum + ", mailTheme=" + mailTheme
				+ ", secondFloorDate=" + secondFloorDate + ", filePathAndName=" + filePathAndName + ", resolution="
				+ resolution + "]";
	}
	
	

}

module com.bensherriff.caesar {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires java.xml.bind;

    opens com.bensherriff.caesar.data;
    opens com.bensherriff.caesar.ui;
}
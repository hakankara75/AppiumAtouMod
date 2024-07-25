package pages;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class OtelZ_Page {
    public OtelZ_Page(){
        PageFactory.initElements(new AppiumFieldDecorator(Driver.getDriver()),this);
    }

    @AndroidFindBy(id = "com.otelz.mobil:id/recycler_popular_searches_home")
    public WebElement tanitimSayfasi;
    @AndroidFindBy(id = "com.otelz.mobil:id/tv_search_label")
    public WebElement otel;
    @AndroidFindBy(id = "com.otelz.mobil:id/search_src_text")
    public WebElement otelText;
    @AndroidFindBy(id = "com.otelz.mobil:id/constraintSearch")
    public List<WebElement> otelSecenekler;
    @AndroidFindBy(id = "com.otelz.mobil:id/tv_start_date")
    public WebElement startDate;
    @AndroidFindBy(id = "com.otelz.mobil:id/tv_day_number")
    public List<WebElement> startDateNumber;
    @AndroidFindBy(id = "com.otelz.mobil:id/tvCheckOutValue")
    public WebElement checkoutNumber;
    @AndroidFindBy(id = "com.otelz.mobil:id/relativeBtnChooseDate")
    public WebElement apply;
    @AndroidFindBy(id = "com.otelz.mobil:id/view_search_background")
    public WebElement arama;
    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Map\")")
    public WebElement map;
    @AndroidFindBy(id = "com.otelz.mobil:id/textView21")
    public List<WebElement> otels;




    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Activity\")")
    public WebElement activity;
    @AndroidFindBy(accessibility = "45")
    public WebElement num45;
    @FindBy(xpath = "//android.widget.TextView[contains(@text=\"API\")]")
    public WebElement apiTitle;

    @AndroidFindBy(id = "android:id/text1")
    public List<WebElement> accessibilityNodeQuerying;
    @AndroidFindBy(id = "io.appium.android.apis:id/tasklist_finished")
    public List<WebElement> checkBoxes;
    @AndroidFindBy(xpath = "(//android.widget.CheckBox[@resource-id=\"io.appium.android.apis:id/tasklist_finished\"])[3]")
    public WebElement conquerWorld;
    @AndroidFindBy(id = "android:id/text1")
    public List<WebElement> app;


    @FindBy(id = "com.touchboarder.android.api.demos:id/left_text")
    public WebElement customTitlePage;
    @FindBy(id = "com.touchboarder.android.api.demos:id/left_text_edit")
    public WebElement leftBox;
    @FindBy(id = "com.touchboarder.android.api.demos:id/right_text_edit")
    public WebElement rightBox;
    @FindBy(id = "com.touchboarder.android.api.demos:id/left_text_button")
    public WebElement leftButton;
    @FindBy(id = "com.touchboarder.android.api.demos:id/right_text_button")
    public WebElement rightButton;
    @FindBy(id = "com.touchboarder.android.api.demos:id/welcome_text")
    public WebElement appInvitePage;

    @FindBy(xpath = "//android.widget.TextView[contains(@text=\"Preference\")]")
    public WebElement preference;
    @FindBy(xpath = "//android.widget.TextView[contains(@text=\"1. Preferences from XML\")]")
    public WebElement preferenceArea;
    @FindBy(xpath = "//android.widget.TextView[contains(@text=\"3. Preference dependencies\")]")
    public WebElement preferenceDependencies;
    @FindBy(id = "android:id/widget_frame")
    public WebElement wifiCheckBox;
    @FindBy(xpath = "//android.widget.TextView[contains(@text=\"WiFi settings\")]")
    public WebElement wifiSettings;
    @FindBy(id = "android:id/alertTitle")
    public WebElement wifiSettingsPopup;
    @FindBy(id = "android:id/edit")
    public WebElement wifiSettingsTextBox;
    @FindBy(id = "android:id/button1")
    public WebElement okButton;
    @FindBy(xpath = "//android.widget.TextView[@text=\"9. Switch\"]")
    public WebElement switchButton;
    @FindBy(xpath = "//android.widget.CheckBox[@resource-id=\"android:id/checkbox\"]")
    public WebElement checkBox;
    @FindBy(xpath = "//android.widget.Switch[@text=\"ON\"]")
    public WebElement switchPreference;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Views\"]")
    public WebElement viewLink;
    @FindBy(xpath = "//android.widget.TextView[@text=\"Drag and Drop\"]")
    public WebElement dragAndDrop;
    @FindBy(id = "com.touchboarder.android.api.demos:id/drag_dot_1")
    public WebElement dragAndDropOne;
    @FindBy(id = "com.touchboarder.android.api.demos:id/drag_dot_2")
    public WebElement dragAndDropTwo;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"API Demos\")")
    public WebElement notAMember;



}

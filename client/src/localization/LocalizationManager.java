package localization;

public class LocalizationManager {
    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    private String Language = "RU";
    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }

    private Lang lang;

    public LocalizationManager(Lang lang) {
        this.lang = lang;
    }

}

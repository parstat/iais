package com.nbs.iais.ms.common.enums;

public enum Language {

    ENG, ROM, RUS;

    public final static String LANGUAGE_LONG_ENG = "eng";
    public final static String LANGUAGE_SHORT_ENG = "en";

    public final static String LANGUAGE_LONG_ROM = "rom";
    public final static String LANGUAGE_SHORT_ROM = "ro";

    public final static String LANGUAGE_LONG_RUS = "rus";
    public final static String LANGUAGE_SHORT_RUS = "ru";


    public static Language getLanguage(final String language) {
        if(language.equalsIgnoreCase(LANGUAGE_LONG_ENG) || language.equalsIgnoreCase(LANGUAGE_SHORT_ENG)) {
            return Language.ENG;
        }

        if(language.equalsIgnoreCase(LANGUAGE_LONG_ROM) || language.equalsIgnoreCase(LANGUAGE_SHORT_ROM)) {
            return Language.ROM;
        }

        if (language.equalsIgnoreCase(LANGUAGE_LONG_RUS) || language.equalsIgnoreCase(LANGUAGE_SHORT_RUS)) {
            return Language.RUS;
        }
        //default
        return getDefault();
    }

    public static String getLanguageShort(final String language) {

        final Language lng = getLanguage(language);

        return lng.getShortName();
    }

    public String getShortName() {

        if(this == Language.ENG) {
            return LANGUAGE_SHORT_ENG;
        }

        if(this == Language.RUS) {
            return LANGUAGE_SHORT_RUS;
        }

        return LANGUAGE_SHORT_ROM;
    }

    public static Language getDefault() {
        return Language.ENG;
    }

}

package com.nbs.iais.ms.common.enums;

public enum PhaseName {

    SPECIFY_NEEDS,
    DESIGN,
    BUILD,
    COLLECT,
    PROCESS,
    ANALYSE,
    DISSEMINATE,
    EVALUATE;

    public static String SPECIFY_NEEDS_ENG = "Specify needs";
    public static String SPECIFY_NEEDS_ROM = "Precizați nevoile";
    public static String SPECIFY_NEEDS_RUS = "Укажите потребности";

    public static String DESIGN_ENG = "Design";
    public static String DESIGN_ROM = "Proiecta";
    public static String DESIGN_RUS = "дизайн";

    public static String BUILD_ENG = "Build";
    public static String BUILD_ROM = "Construi";
    public static String BUILD_RUS = "Сложение";

    public static String COLLECT_ENG = "Collect";
    public static String COLLECT_ROM = "Colectarea";
    public static String COLLECT_RUS = "Cобирать";

    public static String PROCESS_ENG = "Process";
    public static String PROCESS_ROM = "Proces";
    public static String PROCESS_RUS = "Обработать";

    public static String ANALYSE_ENG = "Analyse";
    public static String ANALYSE_ROM = "Analiza";
    public static String ANALYSE_RUS = "анализировать";

    public static String DISSEMINATE_ENG = "Disseminate";
    public static String DISSEMINATE_ROM = "Difuzări";
    public static String DISSEMINATE_RUS = "Распространять";

    public static String EVALUATE_ENG = "Evaluate";
    public static String EVALUATE_ROM = "Evalua";
    public static String EVALUATE_RUS = "оценивать";


    public static PhaseName fromId(final int i) {
        if(i == 1) {
            return SPECIFY_NEEDS;
        }
        if(i == 2) {
            return DESIGN;
        }
        if(i == 3) {
            return BUILD;
        }
        if(i == 4) {
            return COLLECT;
        }
        if(i == 5) {
            return PROCESS;
        }

        if(i == 6) {
            return ANALYSE;
        }
        if(i == 7) {
            return DISSEMINATE;
        }
        //i = 8
        return EVALUATE;
    }

    public static int fromPhase(final PhaseName phaseName) {
        if(phaseName == PhaseName.SPECIFY_NEEDS) {
            return 1;
        }

        if(phaseName == PhaseName.DESIGN) {
            return 2;
        }

        if(phaseName == PhaseName.BUILD) {
            return 3;
        }

        if(phaseName == PhaseName.COLLECT) {
            return 4;
        }

        if(phaseName == PhaseName.PROCESS) {
            return 5;
        }

        if(phaseName == PhaseName.ANALYSE) {
            return 6;
        }

        if(phaseName == PhaseName.DISSEMINATE) {
            return 7;
        }

        if(phaseName == PhaseName.EVALUATE) {
            return 8;
        }

        return 0;
    }

    public String translate(final Language language) {
        if(this == SPECIFY_NEEDS) {
            if(language == Language.ENG) {
                return SPECIFY_NEEDS_ENG;
            }
            if(language == Language.ROM) {
                return SPECIFY_NEEDS_ROM;
            }
            if(language == Language.RUS) {
                return SPECIFY_NEEDS_RUS;
            }
        }

        if(this == DESIGN) {
            if(language == Language.ENG) {
                return DESIGN_ENG;
            }
            if(language == Language.ROM) {
                return DESIGN_ROM;
            }
            if(language == Language.RUS) {
                return DESIGN_RUS;
            }
        }

        if(this == BUILD) {
            if(language == Language.ENG) {
                return BUILD_ENG;
            }
            if(language == Language.ROM) {
                return BUILD_ROM;
            }
            if(language == Language.RUS) {
                return BUILD_RUS;
            }
        }

        if(this == COLLECT) {
            if(language == Language.ENG) {
                return COLLECT_ENG;
            }
            if(language == Language.ROM) {
                return COLLECT_ROM;
            }
            if(language == Language.RUS) {
                return COLLECT_RUS;
            }
        }

        if(this == PROCESS) {
            if(language == Language.ENG) {
                return PROCESS_ENG;
            }
            if(language == Language.ROM) {
                return PROCESS_ROM;
            }
            if(language == Language.RUS) {
                return PROCESS_RUS;
            }
        }

        if(this == ANALYSE) {
            if(language == Language.ENG) {
                return ANALYSE_ENG;
            }
            if(language == Language.ROM) {
                return ANALYSE_ROM;
            }
            if(language == Language.RUS) {
                return ANALYSE_RUS;
            }
        }

        if(this == DISSEMINATE) {
            if(language == Language.ENG) {
                return DISSEMINATE_ENG;
            }
            if(language == Language.ROM) {
                return DISSEMINATE_ROM;
            }
            if(language == Language.RUS) {
                return DISSEMINATE_RUS;
            }
        }

        if(this == EVALUATE) {
            if(language == Language.ENG) {
                return EVALUATE_ENG;
            }
            if(language == Language.RUS) {
                return EVALUATE_RUS;
            }
        }

        return EVALUATE_ROM;
    }
}

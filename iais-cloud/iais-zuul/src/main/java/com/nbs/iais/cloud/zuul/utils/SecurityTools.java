package com.nbs.iais.cloud.zuul.utils;

import com.nbs.iais.ms.common.utils.StringTools;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SecurityTools {

    private final static Logger LOG = LogManager.getLogger(SecurityTools.class);
    private static final String[] UNSECURE_REGEXES = {".*<\\s*applet(.|\n|\r)*?>(.|\n|\r)*?",
            ".*<\\s*body(.|\n|\r)*?>(.|\n|\r)*?", ".*<\\s*embed(.|\n|\r)*?>(.|\n|\r)*?",
            ".*<\\s*frame(.|\n|\r)*?>(.|\n|\r)*?", ".*<\\s*script(.|\n|\r)*?>(.|\n|\r)*?",
            ".*<\\s*frameset(.|\n|\r)*?>(.|\n|\r)*?", ".*<\\s*html(.|\n|\r)*?>(.|\n|\r)*?",
            ".*<\\s*iframe(.|\n|\r)*?>(.|\n|\r)*?", ".*<\\s*img(.|\n|\r)*?>(.|\n|\r)*?",
            ".*<\\s*style(.|\n|\r)*?>(.|\n|\r)*?", ".*<\\s*layer(.|\n|\r)*?>(.|\n|\r)*?",
            ".*<\\s*link(.|\n|\r)*?>(.|\n|\r)*?", ".*<\\s*ilayer(.|\n|\r)*?>(.|\n|\r)*?",
            ".*<\\s*meta(.|\n|\r)*?>(.|\n|\r)*?", ".*<\\s*object(.|\n|\r)*?>(.|\n|\r)*?",};

    /**
     * We
     *
     * @param string
     * @return True if it is secure
     */
    public static boolean isSecure(final String string) {

        if (StringTools.isNotEmpty(string)) {

            for (final String regex : UNSECURE_REGEXES) {

                if (string.matches(regex)) {
                    return false;
                }
            }
        }

        return true;
    }
}

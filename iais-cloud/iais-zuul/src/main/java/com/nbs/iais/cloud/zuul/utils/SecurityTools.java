package com.nbs.iais.cloud.zuul.utils;

import com.nbs.iais.ms.common.utils.StringTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SecurityTools {

    private final static Logger LOG = LoggerFactory.getLogger(SecurityTools.class);
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
     * @param string the string to check
     * @return True if it is not secure
     */
    public static boolean isNotSecure(final String string) {

        if (StringTools.isNotEmpty(string)) {

            for (final String regex : UNSECURE_REGEXES) {

                if (string.matches(regex)) {
                    return true;
                }
            }
        }

        return false;
    }
}

package com.nbs.iais.ms.common.db.domains.translators;

import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.BusinessFunction;
import com.nbs.iais.ms.common.db.domains.interfaces.gsim.group.business.StatisticalProgram;
import com.nbs.iais.ms.common.db.domains.interfaces.security.Account;
import com.nbs.iais.ms.common.dto.DTO;
import com.nbs.iais.ms.common.dto.impl.AccountDTO;
import com.nbs.iais.ms.common.dto.impl.BusinessFunctionDTO;
import com.nbs.iais.ms.common.dto.impl.StatisticalProgramDTO;
import com.nbs.iais.ms.common.dto.wrappers.DTOList;
import com.nbs.iais.ms.common.enums.Language;
import com.nbs.iais.ms.common.enums.PhaseName;

import javax.persistence.GeneratedValue;
import java.util.Optional;

public class Translator {


    private String rootUrl;

    public static <A extends Account> Optional<AccountDTO> translate(final A account) {

        if(account == null) {
            return Optional.empty();
        }
        final AccountDTO accountDTO = new AccountDTO(account.getId());

        accountDTO.setUsername(account.getUsername());
        accountDTO.setRole(account.getRole());
        accountDTO.setStatus(account.getStatus());
        accountDTO.setName(account.getName());
        accountDTO.setLink("/accounts/" + account.getId().toString());
        return Optional.of(accountDTO);

    }


    public static <A extends Account> Optional<DTOList<AccountDTO>> translate(final Iterable<A> accounts) {

        if(!accounts.iterator().hasNext()) {
            return Optional.empty();
        }
        final DTOList<AccountDTO> accountDTOS = DTOList.empty(AccountDTO.class);

        accounts.forEach(accountEntity -> translate(accountEntity)
                .ifPresent(accountDTOS::add));

        return Optional.of(accountDTOS);
    }


    public static <SP extends StatisticalProgram> Optional<StatisticalProgramDTO> translate(final SP statisticalProgram,
                                                                                  final Language language) {

        if(statisticalProgram == null) {
            return Optional.empty();
        }
        final StatisticalProgramDTO statisticalProgramDTO = new StatisticalProgramDTO(statisticalProgram.getId());
        statisticalProgramDTO.setBudget(statisticalProgram.getBudget());
        statisticalProgramDTO.setDateEnded(statisticalProgram.getDateEnded());
        statisticalProgramDTO.setDateInitiated(statisticalProgram.getDateInitiated());
        statisticalProgramDTO.setProgramStatus(statisticalProgram.getProgramStatus());
        statisticalProgramDTO.setSourceOfFunding(statisticalProgram.getSourceOfFounding());
        statisticalProgramDTO.setName(statisticalProgram.getName(language));
        statisticalProgramDTO.setDescription(statisticalProgram.getDescription(language));
        return Optional.of(statisticalProgramDTO);

    }

    public static <SP extends StatisticalProgram> Optional<DTOList<StatisticalProgramDTO>> translate(
            final Iterable<SP> statisticalPrograms, final Language language) {
        if(statisticalPrograms == null || !statisticalPrograms.iterator().hasNext() ) {
            return Optional.empty();
        }
        final DTOList<StatisticalProgramDTO> statisticalProgramDTOS = DTOList.empty(StatisticalProgramDTO.class);

        statisticalPrograms.forEach(sp ->
                translate(sp, language).ifPresent(statisticalProgramDTOS::add));

        return Optional.of(statisticalProgramDTOS);

    }

    public static <BF extends BusinessFunction> Optional<BusinessFunctionDTO> translate(
            final BF businessFunction, final Language language) {

        if(businessFunction == null) {
            return Optional.empty();
        }

        final BusinessFunctionDTO businessFunctionDTO = new BusinessFunctionDTO(businessFunction.getId());
        businessFunctionDTO.setName(businessFunction.getName(language));
        businessFunctionDTO.setDescription(businessFunction.getDescription(language));
        businessFunctionDTO.setLocalId(businessFunction.getLocalId());
        businessFunctionDTO.setVersion(businessFunction.getVersion());
        businessFunctionDTO.setVersionDate(businessFunction.getVersionDate());
        businessFunctionDTO.setVersionRationale(businessFunction.getVersionRationale());
        businessFunctionDTO.setPhaseId(Integer.parseInt(businessFunction.getLocalId().substring(0, 1)));
        businessFunctionDTO.setPhase(PhaseName.fromId(businessFunctionDTO.getPhaseId()).translate(language));

        return Optional.of(businessFunctionDTO);

    }
}

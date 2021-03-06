package com.archana.demospringboot16thdec.service;

import com.archana.demospringboot16thdec.model.BalanceSheet;
import com.archana.demospringboot16thdec.model.User;
import com.archana.demospringboot16thdec.repository.BalanceSheetRepo;
import com.archana.demospringboot16thdec.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.logging.Logger;


@Service
public class BalanceSheetService {
    public static final String ASSET = "ASSET";
    public static final String LIABILITY = "LIABILITY";
    @Autowired
    private BalanceSheetRepo balanceSheetRepo;
    @Autowired
    private UserRepository userRepository;

    public BalanceSheetService(BalanceSheetRepo balanceSheetRepo, UserRepository userRepository) {
        this.balanceSheetRepo = balanceSheetRepo;
        this.userRepository = userRepository;
    }

    public BalanceSheet saveBalanceSheet(BalanceSheet balanceSheet) {
        return balanceSheetRepo.save(balanceSheet);
    }

    public BalanceSheet getBalanceSheetById(int id) {
        return balanceSheetRepo.findById(id).get();
    }


    public Long calculateNetWorth(ArrayList<BalanceSheet> balanceSheets) throws Exception {
        User user = new User();
        user = userRepository.save(user);
        Long netWorth = 0L;
        System.out.println(balanceSheets.size());
        for (BalanceSheet balanceSheet : balanceSheets) {
            System.out.println(balanceSheet.getValue());
            balanceSheet.setUser(user);
            if (balanceSheet.getDescription().equals(ASSET)) {
                netWorth = netWorth + balanceSheet.getValue();
            } else if (balanceSheet.getDescription().equals(LIABILITY)) {
                netWorth = netWorth - balanceSheet.getValue();
            } else {
                throw new Exception("Unknown type");
            }
        }

        balanceSheetRepo.saveAll(balanceSheets);
        return netWorth;
    }


}

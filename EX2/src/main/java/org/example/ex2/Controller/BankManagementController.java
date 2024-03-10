package org.example.ex2.Controller;

import org.example.ex2.Api.ApiResponse;
import org.example.ex2.Model.BankManagement;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/bank")
public class BankManagementController {
    ArrayList<BankManagement> data = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<BankManagement> displayTasks() {
        return data;
    }

    @PostMapping("/add")
    public ApiResponse addCustomer(@RequestBody BankManagement customer) {
        data.add(customer);
        return new ApiResponse("Customer added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateCustomer(@PathVariable int index, @RequestBody BankManagement customer) {
        data.set(index, customer);
        return new ApiResponse("Customer updated");
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteCustomer(@PathVariable int index) {
        data.remove(index);
        return new ApiResponse("Customer deleted");
    }

    @PutMapping("/deposit/{amount}")
    public ArrayList<BankManagement> depositMoney(@PathVariable int amount) {
        ArrayList<BankManagement> deposit = new ArrayList<>();
        for(BankManagement d : data){
            d.setBalance(amount+d.getBalance());
            deposit.add(d);
        }
        return deposit;
    }

    @PostMapping("/withdraw/{amount}")
    public ApiResponse withdrawMoney(@PathVariable int amount) {
        ArrayList<BankManagement> withdraw = new ArrayList<>();

        for (BankManagement w : data) {
            if (w.getBalance()>=amount) {
               w.setBalance(w.getBalance()-amount);
               withdraw.add(w);
            }
            else{
                return new ApiResponse("insuffiecient Funds");
            }
        }
        return new ApiResponse("Transaction complete");
    }
}
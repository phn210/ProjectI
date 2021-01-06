package service.customer;

import model.entity.Customer;
import repository.CustomerRepo;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerService {
    CustomerRepo customerRepo;

    public CustomerService(){
        customerRepo = new CustomerRepo();
    }

    public ArrayList<Customer> getAllCustomer() throws SQLException {
        return customerRepo.findAll();
    }

    public void addCustomer(Customer customer) throws SQLException {
        customerRepo.insert(customer);
    }

    public void updateCustomer(Customer customer) throws SQLException {
        customerRepo.update(customer);
    }
}

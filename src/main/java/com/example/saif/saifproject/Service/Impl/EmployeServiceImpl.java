package com.example.saif.saifproject.Service.Impl;

import com.example.saif.saifproject.Entity.Employe;
import com.example.saif.saifproject.Repository.EmployeRepository;
import com.example.saif.saifproject.Service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeServiceImpl implements EmployeService {


        @Autowired
        private EmployeRepository employeRepository;

        @Override
        public Employe saveEmploye(Employe employe) {
            return employeRepository.save(employe);
        }

        @Override
        public Optional<Employe> getEmployeById(Long id) {
            return employeRepository.findById(id);
        }

        @Override
        public Page<Employe> getAllEmployes(int page, int size, String sortBy) {
            return employeRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
        }

        @Override
        public void deleteEmploye(Long id) {
            employeRepository.deleteById(id);
        }

        @Override
        public List<Employe> getEmployesByNom(String nom) {
            return employeRepository.findByNom(nom);
        }
    }

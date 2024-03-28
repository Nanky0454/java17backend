package org.proyectofinal.service.impl;

import lombok.RequiredArgsConstructor;
import org.proyectofinal.model.Registration;
import org.proyectofinal.repo.IRegistrationRepo;
import org.proyectofinal.repo.IGenericRepo;
import org.proyectofinal.service.IRegistrationService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl extends CRUDImpl<Registration, Integer> implements IRegistrationService {
    //@Autowired
    private final IRegistrationRepo repo;

    @Override
    protected IGenericRepo<Registration, Integer> getRepo() {
        return repo;
    }
}

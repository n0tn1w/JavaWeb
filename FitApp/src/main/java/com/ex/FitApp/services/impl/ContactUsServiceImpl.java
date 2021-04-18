package com.ex.FitApp.services.impl;

import com.ex.FitApp.models.bindings.ContactUsAddBinding;
import com.ex.FitApp.models.entities.ContactUsEntity;
import com.ex.FitApp.repositories.ContactUsRepository;
import com.ex.FitApp.services.ContactUsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ContactUsServiceImpl implements ContactUsService {

    private final ModelMapper modelMapper;
    private final ContactUsRepository contactUsRepository;

    public ContactUsServiceImpl(ModelMapper modelMapper, ContactUsRepository contactUsRepository) {
        this.modelMapper = modelMapper;
        this.contactUsRepository = contactUsRepository;
    }

    @Override
    public void addMessage(ContactUsAddBinding contactUsModel) {
        this.contactUsRepository.save(modelMapper.map(contactUsModel, ContactUsEntity.class));
    }

    @Override
    public void deleteById(Long messageId) {
        this.contactUsRepository.deleteById(messageId);
    }
}

package com.ex.FitApp.web;

import com.ex.FitApp.models.bindings.ContactUsAddBinding;
import com.ex.FitApp.services.ContactUsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ConcatUsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ContactUsController contactUsController;
    @Mock
    private ContactUsService mockContactUsService;
    @Mock
    private BindingResult mockBindingResult;
    @Mock
    private RedirectAttributes mockRedirectAttributes;

    @BeforeEach
    void setUp() {
        contactUsController= new ContactUsController(mockContactUsService);
    }

    @Test
    public void getContactUsShouldReturnStatusOk() throws Exception {
        this.mockMvc.perform(get("/contact-us"))
                .andExpect(status().isOk());
    }
    @Test
    public void confirmContactUsFormShouldSuccessfullyConfirm() throws Exception {
        // Arrange
        ContactUsAddBinding contactUsAddBinding = new ContactUsAddBinding();
        when(this.mockBindingResult.hasErrors())
                .thenReturn(false);

        // Act
        mockMvc.perform(post("/contact-us").with(csrf()))
                .andExpect(status().isFound());
        String result = this.contactUsController.contactUsPost(contactUsAddBinding, mockBindingResult, mockRedirectAttributes);

        // Assert
        assertEquals("redirect:/", result);
        verify(this.mockContactUsService, times(1)).addMessage(contactUsAddBinding);
    }
}

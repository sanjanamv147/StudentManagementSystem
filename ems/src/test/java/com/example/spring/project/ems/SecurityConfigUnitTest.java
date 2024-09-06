package com.example.spring.project.ems;



import com.example.spring.project.ems.Controller.CreateEmployee;
import com.example.spring.project.ems.Service.CustomUserDetailsService;
import com.example.spring.project.ems.Service.EmployeeCreateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@Import(SecurityConfig.class)
public class SecurityConfigUnitTest {

    @Autowired
    private MockMvc mockMvc;
    private CreateEmployee createEmployee;


    @MockBean
    private CustomUserDetailsService userDetailsService;
    private EmployeeCreateService employeeCreateService;

    @Test
    public void whenAccessingUserCreation_thenPermitAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
                .andExpect(status().isOk()); // No authentication needed, expect 200 OK
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void whenAdminAccessingEmployees_thenOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees1/some-id"))
                .andExpect(status().isOk()); // Should be accessible for ADMIN, expect 200 OK
    }

    @Test
    @WithMockUser(roles = "USER")
    public void whenUserAccessingEmployees_thenForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees1/some-id"))
                .andExpect(status().isForbidden()); // USER role should be forbidden, expect 403 Forbidden
    }

    @Test
    public void whenUnauthenticatedAccessingEmployees_thenUnauthorized() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/employees1/some-id"))
                .andExpect(status().isUnauthorized()); // No authentication, expect 401 Unauthorized
    }
}

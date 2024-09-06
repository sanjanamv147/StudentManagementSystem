//package com.example.spring.project.ems;
//
//
//
//import com.example.spring.project.ems.Controller.StudentGetController;
//import com.example.spring.project.ems.DTO.StudentDTO;
//import com.example.spring.project.ems.Service.StudentGetAllService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Arrays;
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.hamcrest.Matchers.hasSize;
//import static org.hamcrest.Matchers.is;
//
//class GetStudentTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private StudentGetAllService studentService;
//
//    @InjectMocks
//    private StudentGetController getStudent;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(getStudent).build();
//    }
//
//    @Test
//    void testGetAllStudents() throws Exception {
//        // Arrange
//        StudentDTO student1 = new StudentDTO(1L, "John Doe", "Engineering", "JAVA",);
//        StudentDTO student2 = new StudentDTO(2L, "Jane Smith", "Marketing","JAVA" );
//        when(studentService.getAllStudents()).thenReturn(Arrays.asList(student1, student2));
//
//        // Act & Assert
//        mockMvc.perform(get("/api/students")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$", hasSize(2)))
//                .andExpect(jsonPath("$[0].id", is(1)))
//                .andExpect(jsonPath("$[0].name", is("John Doe")))
//                .andExpect(jsonPath("$[0].email", is("john@gmail.com")))
//                .andExpect(jsonPath("$[0].department", is("JAVA")))
//                .andExpect(jsonPath("$[1].id", is(2)))
//                .andExpect(jsonPath("$[1].name", is("Jane Smith")))
//                .andExpect(jsonPath("$[0].email", is("jane@gmail.com")))
//                .andExpect(jsonPath("$[0].department", is("JAVA")));
//    }
//
//    @Test
//    void testGetStudentById() throws Exception {
//        // Arrange
//        Long studentId = 1L;
//        StudentDTO studentDTO = new StudentDTO(studentId, "John Doe", "Engineering", );
//        when(studentService.getStudentById(studentId)).thenReturn(Optional.of(studentDTO));
//
//        // Act & Assert
//        mockMvc.perform(get("/api/students/{id}", studentId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("John Doe")))
//                .andExpect(jsonPath("$.department", is("Engineering")));
//    }
//
//    @Test
//    void testGetStudentById_NotFound() throws Exception {
//        // Arrange
//        Long studentId = 1L;
//        when(studentService.getStudentById(studentId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        mockMvc.perform(get("/api/students/{id}", studentId)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//}

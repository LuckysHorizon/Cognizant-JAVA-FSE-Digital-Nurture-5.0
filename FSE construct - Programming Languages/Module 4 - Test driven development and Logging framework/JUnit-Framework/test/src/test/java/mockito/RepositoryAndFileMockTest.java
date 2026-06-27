package mockito;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Repository and File Mocking")
class RepositoryAndFileMockTest {

    @Nested
    @DisplayName("Product Repository Mocking")
    class ProductRepoTest {

        @Mock
        private ProductRepository productRepository;

        @InjectMocks
        private ProductService productService;

        @Test
        void testGetProduct() {
            when(productRepository.findByName("Laptop")).thenReturn("Laptop - $999");
            assertEquals("Laptop - $999", productService.getProduct("Laptop"));
        }

        @Test
        void testGetProductNotFound() {
            when(productRepository.findByName("Unknown")).thenReturn(null);
            assertThrows(RuntimeException.class, () -> productService.getProduct("Unknown"));
        }

        @Test
        void testGetProductsByCategory() {
            when(productRepository.findByCategory("Electronics"))
                    .thenReturn(Arrays.asList("Laptop", "Phone", "Tablet"));
            assertEquals(3, productService.getProductsByCategory("Electronics").size());
        }

        @Test
        void testGetTotalProducts() {
            when(productRepository.count()).thenReturn(50L);
            assertEquals(50, productService.getTotalProducts());
        }
    }

    @Nested
    @DisplayName("File Storage Mocking")
    class FileStorageTest {

        @Mock
        private FileStorage fileStorage;

        @InjectMocks
        private ReportGenerator reportGenerator;

        @Test
        void testGenerateReport() {
            when(fileStorage.saveFile(anyString(), anyString())).thenReturn(true);
            assertTrue(reportGenerator.generateReport("Sales", "Q1 Data"));
            verify(fileStorage).saveFile(eq("Sales.txt"), anyString());
        }

        @Test
        void testLoadReport() {
            when(fileStorage.fileExists("Sales.txt")).thenReturn(true);
            when(fileStorage.readFile("Sales.txt")).thenReturn("Report Content");
            assertEquals("Report Content", reportGenerator.loadReport("Sales"));
        }

        @Test
        void testLoadReportNotFound() {
            when(fileStorage.fileExists("Missing.txt")).thenReturn(false);
            assertThrows(RuntimeException.class, () -> reportGenerator.loadReport("Missing"));
        }

        @Test
        void testDeleteReport() {
            when(fileStorage.deleteFile("Sales.txt")).thenReturn(true);
            assertTrue(reportGenerator.deleteReport("Sales"));
        }
    }
}

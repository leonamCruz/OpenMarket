package tech.leonam.openmarket.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@PreAuthorize("hasRole('ROLE_ADMINISTRATIVE')")
@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportController {
}

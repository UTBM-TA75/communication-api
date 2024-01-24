package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Staff;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StaffController {
    private final StaffRepository staffRepository;

    /**
     * Gets all staff members
     *
     * @return the list of staff members
     */
    @GetMapping("/staff-members")
    public ResponseEntity<Iterable<Staff>> getAllStaffMembers() {
        return ResponseEntity.ok(staffRepository.findAll());
    }

    /**
     * Get staff member
     *
     * @param id the staff member's id
     * @return the staff member
     */
    @GetMapping("/staff-members/{id}")
    public ResponseEntity<Staff> getStaffMember(@PathVariable Long id) {
        Staff staff = staffRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No staff member found with id " + id));

        return ResponseEntity.ok(staff);
    }

    /**
     * Creates a staff member
     *
     * @param data the staff member's data
     * @return the created staff member
     */
    @PostMapping("/staff-members")
    public ResponseEntity<Staff> createStaffMember(@RequestBody Staff data) {
        Staff staff = new Staff();
        staff.setIdUser(data.getIdUser());
        staff.setLastName(data.getLastName());
        staff.setFirstName(data.getFirstName());

        staffRepository.save(staff);

        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }

    /**
     * Deletes a staff member
     * @param id the staff member's id
     */
    @DeleteMapping("/staff-members/{id}")
    public void deleteStaffMember(@PathVariable Long id) {
        Staff staff = staffRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No staff member found with id " + id));


        staffRepository.deleteById(staff.getId());
    }

    /**
     * Updates a staff member
     * @param id the staff member's id
     * @param data the staff member's updated data
     * @return the updated staff member
     */
    @PutMapping("/staff-members/{id}")
    public ResponseEntity<Staff> editStaffMember(@PathVariable Long id, @RequestBody Staff data) {
        Staff staff = staffRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No staff member found with id " + id));

        staff.setLastName(data.getLastName());
        staff.setFirstName(data.getFirstName());

        staffRepository.save(staff);

        return ResponseEntity.ok(staff);
    }
}

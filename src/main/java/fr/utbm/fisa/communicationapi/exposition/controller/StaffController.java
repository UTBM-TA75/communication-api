package fr.utbm.fisa.communicationapi.exposition.controller;

import fr.utbm.fisa.communicationapi.infrastructure.entities.Staff;
import fr.utbm.fisa.communicationapi.infrastructure.repositories.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StaffController {
    private final StaffRepository staffRepository;
    @GetMapping("/staffs")
    public Iterable<Staff> get() {
        return staffRepository.findAll();
    }

    @PostMapping("/staffs/create")
    public Staff create(@RequestBody Staff staff) {
        Staff stff = new Staff();
        stff.setIdUser(staff.getIdUser());
        stff.setLastName(staff.getLastName());
        stff.setFirstName(staff.getFirstName());
        stff.setSenderList(staff.getSenderList());
        stff.setTeacherList(staff.getTeacherList());
        return staffRepository.save(stff);
    }

    @GetMapping("/staffs/delete")
    public void delete(@RequestBody Staff staff) {
        staffRepository.deleteById(staff.getId());
    }

    @PostMapping("/staffs/edit")
    public void edit(@RequestBody Staff staff) {
        staffRepository.findById(staff.getId()).map(
                stff -> {
                    stff.setIdUser(staff.getIdUser());
                    stff.setLastName(staff.getLastName());
                    stff.setFirstName(staff.getFirstName());
                    stff.setSenderList(staff.getSenderList());
                    stff.setTeacherList(staff.getTeacherList());
                    return staffRepository.save(stff);
                }
        );
    }
}

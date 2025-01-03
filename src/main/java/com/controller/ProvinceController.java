package com.controller;

import com.model.Customer;
import com.model.DTO.ProvinceDTO;
import com.model.Province;
import com.service.ICustomerService;
import com.service.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    @Autowired
    private IProvinceService provinceService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView listProvince() {
        ModelAndView modelAndView = new ModelAndView("province/list");
        Iterable<ProvinceDTO> provinces = provinceService.countCustomerByProvince();
        modelAndView.addObject("provinces", provinces);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("province") Province province,
                         RedirectAttributes redirectAttributes) {
        provinceService.save(province);
        redirectAttributes.addFlashAttribute("message", "Create new province successfully");
        return "redirect:/provinces";
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateForm(@PathVariable Long id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView modelAndView = new ModelAndView("province/update");
            modelAndView.addObject("province", province.get());
            return modelAndView;
        } else {
            return new ModelAndView("error_404");
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("province") Province province,
                         RedirectAttributes redirect) {
        provinceService.save(province);
        redirect.addFlashAttribute("message", "Update province successfully");
        return "redirect:/provinces";
    }

    @GetMapping("/count")
    public ModelAndView viewProvinceWithCustomerCount(){
        Iterable<ProvinceDTO> items = provinceService.countCustomerByProvince();
        ModelAndView mv = new ModelAndView("province/count");
        mv.addObject("provinces", items);
        return mv;
    }

    @GetMapping("/delete/{id}")
    public String deleteProvince(@PathVariable("id") Long id){
        Optional<Province> provinceOptional = provinceService.findById(id);
        if(!provinceOptional.isPresent()){
            return "error_404";
        }
        provinceService.remove(id);
        return "redirect:/provinces";
    }
}

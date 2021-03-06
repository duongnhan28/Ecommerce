package com.example.ecommerce.services;

import com.example.ecommerce.entitys.Categories;
import com.example.ecommerce.entitys.Products;
import com.example.ecommerce.entitys.Subcategories;
import com.example.ecommerce.forms.EditSubCategoryForm;
import com.example.ecommerce.repositories.CategoriesRepository;
import com.example.ecommerce.repositories.SubcategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubcategoriesService {
    @Autowired
    private SubcategoriesRepository subcategoriesRepository;
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Transactional
    public List<Subcategories> getSubcategories(){
        return subcategoriesRepository.findAll();
    }

    @Transactional
    public void deleteSubcategory(Long id){
        subcategoriesRepository.deleteById(id);
    }
    @Transactional
    public Subcategories findById(Long id){
        return subcategoriesRepository.findById(id).get();
    }
    @Transactional
    public void addSubCategory(String name, int sorting, Categories categories){
        String slug = name.toLowerCase().replace(" ", "-");
        subcategoriesRepository.save(new Subcategories(name, slug, sorting,categories, new ArrayList<Products>()));
    }
    @Transactional
    public EditSubCategoryForm getEditSubcategoryFormById(Long id){
        Subcategories subcategories = findById(id);
        EditSubCategoryForm editSubCategoryForm = new EditSubCategoryForm(id, subcategories.getName(), subcategories.getSorting(), subcategories.getCategory().getId());
        return  editSubCategoryForm;
    }
    @Transactional
    public void editSubCategory(Long id, String name, int sorting, Long categoryid){
        String slug = name.toLowerCase().replace(" ","-");
        Subcategories subcategories = findById(id);
        subcategories.setName(name);
        subcategories.setSlug(slug);
        subcategories.setSorting(sorting);
        subcategories.setCategory(categoriesRepository.findById(categoryid).get());
        subcategoriesRepository.save(subcategories);
    }
    @Transactional
    public List<Subcategories> findAll(){
        return subcategoriesRepository.findAll();

    }
}

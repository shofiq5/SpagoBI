package it.eng.spagobi.kpi.ou.metadata;
// Generated 21-set-2010 12.29.29 by Hibernate Tools 3.1.0 beta3

import java.util.HashSet;
import java.util.Set;


/**
 * SbiOrgUnitHierarchies generated by hbm2java
 */

public class SbiOrgUnitHierarchies  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String label;
     private String name;
     private String description;
     private String target;
     private Set sbiOrgUnitNodeses = new HashSet(0);
     private Set sbiOrgUnitGrants = new HashSet(0);


    // Constructors

    /** default constructor */
    public SbiOrgUnitHierarchies() {
    }

	/** minimal constructor */
    public SbiOrgUnitHierarchies(Integer id, String label) {
        this.id = id;
        this.label = label;
    }
    
    /** full constructor */
    public SbiOrgUnitHierarchies(Integer id, String label, String name, String description, String target, Set sbiOrgUnitNodeses, Set sbiOrgUnitGrants) {
        this.id = id;
        this.label = label;
        this.name = name;
        this.description = description;
        this.target = target;
        this.sbiOrgUnitNodeses = sbiOrgUnitNodeses;
        this.sbiOrgUnitGrants = sbiOrgUnitGrants;
    }
    

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getTarget() {
        return this.target;
    }
    
    public void setTarget(String target) {
        this.target = target;
    }

    public Set getSbiOrgUnitNodeses() {
        return this.sbiOrgUnitNodeses;
    }
    
    public void setSbiOrgUnitNodeses(Set sbiOrgUnitNodeses) {
        this.sbiOrgUnitNodeses = sbiOrgUnitNodeses;
    }

    public Set getSbiOrgUnitGrants() {
        return this.sbiOrgUnitGrants;
    }
    
    public void setSbiOrgUnitGrants(Set sbiOrgUnitGrants) {
        this.sbiOrgUnitGrants = sbiOrgUnitGrants;
    }
   








}

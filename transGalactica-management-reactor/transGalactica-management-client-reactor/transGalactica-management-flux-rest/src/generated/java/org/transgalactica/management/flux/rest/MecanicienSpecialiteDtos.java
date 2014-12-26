//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.11 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2014.12.22 à 09:37:21 PM CET 
//


package org.transgalactica.management.flux.rest;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour MecanicienSpecialiteDtos complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="MecanicienSpecialiteDtos"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mecanicienSpecialite" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MecanicienSpecialiteDtos", propOrder = {
    "mecanicienSpecialite"
})
public class MecanicienSpecialiteDtos {

    protected List<String> mecanicienSpecialite;

    /**
     * Gets the value of the mecanicienSpecialite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mecanicienSpecialite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMecanicienSpecialite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMecanicienSpecialite() {
        if (mecanicienSpecialite == null) {
            mecanicienSpecialite = new ArrayList<String>();
        }
        return this.mecanicienSpecialite;
    }

    public void setMecanicienSpecialite(List<String> value) {
        this.mecanicienSpecialite = null;
        List<String> draftl = this.getMecanicienSpecialite();
        draftl.addAll(value);
    }

}

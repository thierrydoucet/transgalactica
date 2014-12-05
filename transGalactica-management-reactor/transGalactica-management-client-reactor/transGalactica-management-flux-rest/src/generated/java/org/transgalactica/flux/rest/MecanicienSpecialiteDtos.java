//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.5-2 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.16 à 02:38:27 PM CET 
//


package org.transgalactica.flux.rest;

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
 * &lt;complexType name="MecanicienSpecialiteDtos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mecanicienSpecialites" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MecanicienSpecialiteDtos", propOrder = {
    "mecanicienSpecialites"
})
public class MecanicienSpecialiteDtos {

    protected List<String> mecanicienSpecialites;

    /**
     * Gets the value of the mecanicienSpecialites property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mecanicienSpecialites property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMecanicienSpecialites().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMecanicienSpecialites() {
        if (mecanicienSpecialites == null) {
            mecanicienSpecialites = new ArrayList<String>();
        }
        return this.mecanicienSpecialites;
    }

    /**
     * Sets the value of the mecanicienSpecialites property.
     * 
     * @param mecanicienSpecialites
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMecanicienSpecialites(List<String> mecanicienSpecialites) {
        this.mecanicienSpecialites = mecanicienSpecialites;
    }

}

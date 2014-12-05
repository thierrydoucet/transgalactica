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
 * <p>Classe Java pour VaisseauDtos complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="VaisseauDtos">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="vaisseaux" type="{}VaisseauDto" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VaisseauDtos", propOrder = {
    "vaisseaux"
})
public class VaisseauDtos {

    protected List<VaisseauDto> vaisseaux;

    /**
     * Gets the value of the vaisseaux property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the vaisseaux property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVaisseaux().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VaisseauDto }
     * 
     * 
     */
    public List<VaisseauDto> getVaisseaux() {
        if (vaisseaux == null) {
            vaisseaux = new ArrayList<VaisseauDto>();
        }
        return this.vaisseaux;
    }

    /**
     * Sets the value of the vaisseaux property.
     * 
     * @param vaisseaux
     *     allowed object is
     *     {@link VaisseauDto }
     *     
     */
    public void setVaisseaux(List<VaisseauDto> vaisseaux) {
        this.vaisseaux = vaisseaux;
    }

}

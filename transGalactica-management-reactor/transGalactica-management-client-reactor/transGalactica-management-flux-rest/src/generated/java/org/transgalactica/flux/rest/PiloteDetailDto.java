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
 * <p>Classe Java pour PiloteDetailDto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PiloteDetailDto">
 *   &lt;complexContent>
 *     &lt;extension base="{}EmployeDto">
 *       &lt;sequence>
 *         &lt;element name="nombreHeuresVol" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="vaisseaux" type="{}EmployeVaisseau" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PiloteDetailDto", propOrder = {
    "nombreHeuresVol",
    "vaisseaux"
})
public class PiloteDetailDto
    extends EmployeDto
{

    protected int nombreHeuresVol;
    protected List<EmployeVaisseau> vaisseaux;

    /**
     * Obtient la valeur de la propriété nombreHeuresVol.
     * 
     */
    public int getNombreHeuresVol() {
        return nombreHeuresVol;
    }

    /**
     * Définit la valeur de la propriété nombreHeuresVol.
     * 
     */
    public void setNombreHeuresVol(int value) {
        this.nombreHeuresVol = value;
    }

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
     * {@link EmployeVaisseau }
     * 
     * 
     */
    public List<EmployeVaisseau> getVaisseaux() {
        if (vaisseaux == null) {
            vaisseaux = new ArrayList<EmployeVaisseau>();
        }
        return this.vaisseaux;
    }

    /**
     * Sets the value of the vaisseaux property.
     * 
     * @param vaisseaux
     *     allowed object is
     *     {@link EmployeVaisseau }
     *     
     */
    public void setVaisseaux(List<EmployeVaisseau> vaisseaux) {
        this.vaisseaux = vaisseaux;
    }

}

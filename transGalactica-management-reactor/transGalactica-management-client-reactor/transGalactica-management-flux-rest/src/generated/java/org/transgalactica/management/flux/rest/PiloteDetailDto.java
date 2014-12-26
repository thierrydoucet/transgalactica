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
 * <p>Classe Java pour PiloteDetailDto complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PiloteDetailDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://management.transgalactica.org/hr}EmployeDto"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="vaisseaux" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="vaisseau" type="{http://management.transgalactica.org/hr}EmployeVaisseau" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="nombreHeuresVol" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PiloteDetailDto", propOrder = {
    "vaisseaux",
    "nombreHeuresVol"
})
public class PiloteDetailDto
    extends EmployeDto
{

    protected PiloteDetailDto.Vaisseaux vaisseaux;
    protected int nombreHeuresVol;

    /**
     * Obtient la valeur de la propriété vaisseaux.
     * 
     * @return
     *     possible object is
     *     {@link PiloteDetailDto.Vaisseaux }
     *     
     */
    public PiloteDetailDto.Vaisseaux getVaisseaux() {
        return vaisseaux;
    }

    /**
     * Définit la valeur de la propriété vaisseaux.
     * 
     * @param value
     *     allowed object is
     *     {@link PiloteDetailDto.Vaisseaux }
     *     
     */
    public void setVaisseaux(PiloteDetailDto.Vaisseaux value) {
        this.vaisseaux = value;
    }

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
     * <p>Classe Java pour anonymous complex type.
     * 
     * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="vaisseau" type="{http://management.transgalactica.org/hr}EmployeVaisseau" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "vaisseau"
    })
    public static class Vaisseaux {

        protected List<EmployeVaisseau> vaisseau;

        /**
         * Gets the value of the vaisseau property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the vaisseau property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getVaisseau().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EmployeVaisseau }
         * 
         * 
         */
        public List<EmployeVaisseau> getVaisseau() {
            if (vaisseau == null) {
                vaisseau = new ArrayList<EmployeVaisseau>();
            }
            return this.vaisseau;
        }

        public void setVaisseau(List<EmployeVaisseau> value) {
            this.vaisseau = null;
            List<EmployeVaisseau> draftl = this.getVaisseau();
            draftl.addAll(value);
        }

    }

}

//
// Ce fichier a été généré par l'implémentation de référence JavaTM Architecture for XML Binding (JAXB), v2.2.5-2 
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Toute modification apportée à ce fichier sera perdue lors de la recompilation du schéma source. 
// Généré le : 2013.01.16 à 02:38:27 PM CET 
//


package org.transgalactica.flux.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour PiloteCommand complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="PiloteCommand">
 *   &lt;complexContent>
 *     &lt;extension base="{}EmployeCommand">
 *       &lt;sequence>
 *         &lt;element name="nombreHeuresVol" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PiloteCommand", propOrder = {
    "nombreHeuresVol"
})
public class PiloteCommand
    extends EmployeCommand
{

    protected int nombreHeuresVol;

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

}

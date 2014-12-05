-- ============================================================================
-- Script de creation des donn�es de test (utilis�e par les Tests unitaires)
-- ============================================================================

-- ----------------------------------------------------------------------------
-- Table HANGARS
-- ----------------------------------------------------------------------------
INSERT INTO HANGARS VALUES(1, 'Alderaan', 10);
INSERT INTO HANGARS VALUES(2, 'Arakis', 100);
INSERT INTO HANGARS VALUES(3, 'Etoile noire', 1000);

-- ----------------------------------------------------------------------------
-- Table VAISSEAUX & VAISSEAUX_INTER_GALACTIQUE
-- ----------------------------------------------------------------------------
INSERT INTO VAISSEAUX VALUES(1, 'cargo YT-1300', 'Flying Bird', 6, 100000, 95, 100000, 1);
INSERT INTO VAISSEAUX VALUES(2, 'cargo YT-1300', 'Faucon Millenium', 6, 100000, 105, 100000, 1);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE values (2, 2);
INSERT INTO VAISSEAUX VALUES(3, 'Firefly', 'Serenity', 8, 150000, 95, 150000, 1);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE values (3, 0.5);
INSERT INTO VAISSEAUX VALUES(4, 'Tie Advanced', 'Dark Vador''s Tie Advanced', 0, 0, 103, 1000, 3);
INSERT INTO VAISSEAUX VALUES(5, 'T-65 X-Wings', 'Leader rouge', 0, 110, 100, 15000, null);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE values (5, 1);
INSERT INTO VAISSEAUX VALUES(6, 'Tie fighter', 'DS-61-2 (Mauler Mithel)', 0, 0, 100, 1000, null);

-- ----------------------------------------------------------------------------
-- Table EMPLOYES
-- ----------------------------------------------------------------------------
INSERT INTO EMPLOYES VALUES(1, 'PILOTE', 'Han Solo', '1977-06-09', 542);
INSERT INTO EMPLOYES VALUES(2, 'MECANICIEN', 'Chewbacca', '1977-06-09', null);
INSERT INTO EMPLOYES VALUES(3, 'PILOTE', 'Lando Calrissian', '1980-08-20', 228);
INSERT INTO EMPLOYES VALUES(4, 'PILOTE', 'Hoban Washburne (Wash)', '2002-09-20', 265);
INSERT INTO EMPLOYES VALUES(5, 'MECANICIEN', 'Kaywinnet Lee Frye (Kaylee)', '2002-09-20', null);
INSERT INTO EMPLOYES VALUES(6, 'PILOTE', 'Dark Vador', '1977-06-09', 1289);
INSERT INTO EMPLOYES VALUES(7, 'MECANICIEN', 'D2-R2', '1977-06-09', null);

-- ----------------------------------------------------------------------------
-- Table AFFECTATION_EMPLOYES_VAISSEAUX
-- ----------------------------------------------------------------------------
INSERT INTO AFFECTATION_EMPLOYES_VAISSEAUX VALUES(1, 2);
INSERT INTO AFFECTATION_EMPLOYES_VAISSEAUX VALUES(2, 2);
INSERT INTO AFFECTATION_EMPLOYES_VAISSEAUX VALUES(4, 3);
INSERT INTO AFFECTATION_EMPLOYES_VAISSEAUX VALUES(5, 3);
INSERT INTO AFFECTATION_EMPLOYES_VAISSEAUX VALUES(6, 4);


-- ----------------------------------------------------------------------------
-- Table AFFECTATION_MECANICIENS_SPECIALITES
-- ----------------------------------------------------------------------------
INSERT INTO AFFECTATION_MECANICIENS_SPECIALITES VALUES(2, 3);
INSERT INTO AFFECTATION_MECANICIENS_SPECIALITES VALUES(5, 1);
INSERT INTO AFFECTATION_MECANICIENS_SPECIALITES VALUES(5, 2);
-- ----------------------------------------------------------------------------
-- Validation traitement
-- ----------------------------------------------------------------------------
COMMIT;

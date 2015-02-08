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
-- Validation traitement
-- ----------------------------------------------------------------------------
COMMIT;

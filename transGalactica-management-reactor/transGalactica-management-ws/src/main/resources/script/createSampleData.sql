-- ============================================================================
-- Script de creation des donnees de test (utilisee par les Tests unitaires)
-- ============================================================================

-- --------------------------------------------------------------------------
-- Table HANGARS (numero, localisation, nombre_emplacements)
-- ----------------------------------------------------------------------------
INSERT INTO HANGARS VALUES(1, 'Alderaan', 12500);
INSERT INTO HANGARS VALUES(2, 'Arrakis', 12256);
INSERT INTO HANGARS VALUES(3, 'Corellia', 11000); 
INSERT INTO HANGARS VALUES(4, 'Coruscant', 12240); 
INSERT INTO HANGARS VALUES(5, 'Endor', 4900);
INSERT INTO HANGARS VALUES(6, 'Etoile noire', 120);
INSERT INTO HANGARS VALUES(7, 'Hoth', 7200);
INSERT INTO HANGARS VALUES(8, 'Mustafar', 4200);
INSERT INTO HANGARS VALUES(9, 'Naboo', 12120);
INSERT INTO HANGARS VALUES(10, 'Tatooine', 10465);

-- ----------------------------------------------------------------------------
-- Table VAISSEAUX & VAISSEAUX_INTER_GALACTIQUE
-- (id, modele, immatriculation, nombre_passager_max, capacite_fret (en tonnes), vitesse (en km/h), autonomie (en jours), id_hangar)
-- (id, multiplicateur_hyperdrive)
-- informations glanée sur internet a partir du JDR D6 StarWars (http://rpggamer.org)
-- ----------------------------------------------------------------------------
INSERT INTO VAISSEAUX VALUES(1, 'Cargo YT-1300', 'Flying Bird', 6, 100, 800, 60, 2);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (1, 2); -- Hyperpropulsion
INSERT INTO VAISSEAUX VALUES(2, 'Cargo YT-1300', 'Faucon Millenium', 6, 100, 1200, 60, 1);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (2, 0.5); -- Hyperpropulsion
INSERT INTO VAISSEAUX VALUES(3, 'T-65 X-Wings', 'Red Leader', 0, 0.11, 1050, 7, 1);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (3, 1); -- Hyperpropulsion
INSERT INTO VAISSEAUX VALUES(4, 'T-65 X-Wings', 'Red Two', 0, 0.11, 1050, 7, 1);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (4, 1); -- Hyperpropulsion
INSERT INTO VAISSEAUX VALUES(5, 'T-65 X-Wings', 'Red Three', 0, 0.11, 1050, 7, 1);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (5, 1); -- Hyperpropulsion
INSERT INTO VAISSEAUX VALUES(6, 'T-65 X-Wings', 'Red Four', 0, 0.11, 1050, 7, 1);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (6, 1); -- Hyperpropulsion

INSERT INTO VAISSEAUX VALUES(20, 'Tie Advanced', 'Dark Vador''s Tie Advanced', 0, 0.075, 1250, 2, 6);
INSERT INTO VAISSEAUX VALUES(21, 'Tie fighter', 'DS-61-2 (Mauler Mithel)', 0, 0.065, 1200, 2, 6);
INSERT INTO VAISSEAUX VALUES(22, 'Tie fighter', 'DS-61-3 (Backstabber)', 0, 0.065, 1200, 2, 6);
INSERT INTO VAISSEAUX VALUES(23, 'Tie fighter', 'DS-61-4 (Dark Curse)', 0, 0.065, 1200, 2, 6);

INSERT INTO VAISSEAUX VALUES(40, 'Firefly', 'Serenity', 8, 20000, 950, 365, 10);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (40, 2); -- Hyperpropulsion

INSERT INTO VAISSEAUX VALUES(50, 'Vipers Mark II', 'N7242C', 0, 0, 1000, 7, null);
INSERT INTO VAISSEAUX VALUES(51, 'Vipers Mark II', '1104NC', 0, 0, 1000, 7, null);
INSERT INTO VAISSEAUX VALUES(52, 'Vipers Mark VII', '8757NC', 0, 0, 1200, 7, null);
INSERT INTO VAISSEAUX VALUES(53, 'Raptor', 'Raptor 312', 10, 0.1, 1050, 14, null);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (53, 3); -- FTL
INSERT INTO VAISSEAUX VALUES(54, 'Furtive Viper', 'Blackbird', 0, 0, 1350, 7, null);
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (54, 3); -- FTL
INSERT INTO VAISSEAUX VALUES(55, 'Battlestar (BS-75)', 'Galactica', 100, 50000, 850, 1800, null); -- 5 ans
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (55, 3); -- FTL

INSERT INTO VAISSEAUX VALUES(70, 'Warship Glorious Heritage Class', 'Andromeda Ascendant', 500, 2500, 1050, 1450, 9); -- 4 ans
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (70, 1); -- Slipstream
INSERT INTO VAISSEAUX VALUES(71, 'Slipstream-capable cargo ship', 'Eureka Maru', 10, 5300, 1000, 180, 9); -- 6 mois
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (71, 2); -- Slipstream

INSERT INTO VAISSEAUX VALUES(90, 'Courrier classe Archange', 'Raphaël', 3, 0, 1200, 1800, 3); -- 5 ans
INSERT INTO VAISSEAUX_INTER_GALACTIQUE VALUES (90, 0); -- Gédéon

-- ----------------------------------------------------------------------------
-- Validation traitement
-- ----------------------------------------------------------------------------
COMMIT;

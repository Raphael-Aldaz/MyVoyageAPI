# Routes du contrôleur HotelsController

## Récupérer tous les hôtels
GET /api/hotels?page=<page>
Retourne tous les hôtels paginés.

## Récupérer les hôtels par ville
GET /api/hotelsBy?page=<page>&id=<id>
Récupère les hôtels par ville en fonction de l'ID de la ville.

## Rechercher des villes par mot-clé
GET /api/hotelsByDest?page=<page>&kw=<keyword>
Recherche des villes par mot-clé et retourne les hôtels correspondants.

## Récupérer un hôtel par ID
GET /api/hotels/<id>
Récupère un hôtel en fonction de l'ID.

## Créer un nouvel hôtel
POST /api/hotel
Crée un nouvel hôtel avec les paramètres fournis.

## Mettre à jour un hôtel par ID
PUT /api/hotels/<id>
Met à jour un hôtel en fonction de l'ID et des paramètres fournis.

## Supprimer un hôtel par ID
DELETE /api/hotel/<id>
Supprime un hôtel en fonction de l'ID.

## Récupérer la photo d'un hôtel par ID
GET /api/photo/<id>
Récupère la photo d'un hôtel en fonction de l'ID.

# Routes du contrôleur HotelsController

## Récupérer tous les hôtels
**Méthode :** GET  
**Endpoint :** /api/hotels?page=<page>  
**Description :** Récupère tous les hôtels paginés.

## Récupérer les hôtels par ville
**Méthode :** GET  
**Endpoint :** /api/hotelsBy?page=<page>&id=<id>  
**Description :** Récupère les hôtels par ville en fonction de l'ID de la ville.

## Rechercher des villes par mot-clé
**Méthode :** GET  
**Endpoint :** /api/hotelsByDest?page=<page>&kw=<keyword>  
**Description :** Recherche des villes par mot-clé et retourne les hôtels correspondants.

## Récupérer un hôtel par ID
**Méthode :** GET  
**Endpoint :** /api/hotels/<id>  
**Description :** Récupère un hôtel en fonction de l'ID.

## Créer un nouvel hôtel
**Méthode :** POST  
**Endpoint :** /api/hotel  
**Description :** Crée un nouvel hôtel avec les paramètres fournis.

## Mettre à jour un hôtel par ID
**Méthode :** PUT  
**Endpoint :** /api/hotels/<id>  
**Description :** Met à jour un hôtel en fonction de l'ID et des paramètres fournis.

## Supprimer un hôtel par ID
**Méthode :** DELETE  
**Endpoint :** /api/hotel/<id>  
**Description :** Supprime un hôtel en fonction de l'ID.

## Récupérer la photo d'un hôtel par ID
**Méthode :** GET  
**Endpoint :** /api/photo/<id>  
**Description :** Récupère la photo d'un hôtel en fonction de l'ID.

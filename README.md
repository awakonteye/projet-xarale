# Projet Xarale

Application CRUD complète avec backend Spring Boot et frontend Angular.

## Prérequis

Avant de commencer, assurez-vous d'avoir installé :

- [Docker](https://www.docker.com/get-started) (version 20.10 ou supérieure)
- [Docker Compose](https://docs.docker.com/compose/install/) (version 2.0 ou supérieure)

Vérifiez l'installation avec :

```bash
docker --version
docker compose version
```

## Cloner le projet

Clonez le dépôt sur votre machine locale :

```bash
git clone <URL_DU_DEPOT>
cd projet-xarale
```

## Lancer l'application avec Docker

### Démarrage

Pour lancer tous les services (MySQL, backend Spring Boot, frontend Angular) :

```bash
docker compose up --build
```

La première fois, cela peut prendre quelques minutes car Docker doit :

- Télécharger les images de base (MySQL, Maven, Node.js)
- Construire les images personnalisées pour le backend et le frontend
- Installer les dépendances

### Démarrage en arrière-plan

Pour lancer les services en arrière-plan :

```bash
docker compose up -d --build
```

### Vérifier le statut des services

```bash
docker compose ps
```

### Voir les logs

Pour voir les logs de tous les services :

```bash
docker compose logs -f
```

Pour voir les logs d'un service spécifique :

```bash
docker compose logs -f backend
docker compose logs -f frontend
docker compose logs -f mysql
```

## Accès à l'application

Une fois les services démarrés, vous pouvez accéder à :

- **Frontend Angular** : <http://localhost:4000>
- **Backend Spring Boot API** : <http://localhost:2000>
- **Base de données MySQL** : localhost:3306

### Informations de connexion MySQL

- **Host** : localhost
- **Port** : 3306
- **Database** : crud-xarala
- **Username** : root
- **Password** : rootpassword
- **User alternatif** : xarala / xaralapassword

## Commandes utiles

### Arrêter les services

```bash
docker compose down
```

### Arrêter et supprimer les volumes (⚠️ supprime les données de la base)

```bash
docker compose down -v
```

### Reconstruire les images

```bash
docker compose build --no-cache
```

### Redémarrer un service spécifique

```bash
docker compose restart backend
docker compose restart frontend
```

### Exécuter une commande dans un conteneur

```bash
# Backend
docker compose exec backend bash

# Frontend
docker compose exec frontend sh
```

## Architecture

L'application est composée de 3 services :

1. **mysql** : Base de données MySQL 8.0
2. **backend** : API REST Spring Boot (port 2000)
3. **frontend** : Application Angular (port 4000)

Les services communiquent via un réseau Docker dédié (`xarala-network`).

## Hot Reload

Le hot reload est activé pour le développement :

- **Backend** : Spring Boot DevTools avec rechargement automatique
- **Frontend** : Angular avec rechargement automatique via Chokidar

Les modifications du code sont automatiquement détectées et l'application se recharge.

## Dépannage

### Les services ne démarrent pas

Vérifiez que les ports 2000, 4000 et 3306 ne sont pas déjà utilisés :

```bash
# macOS/Linux
lsof -i :2000
lsof -i :4000
lsof -i :3306

# Windows
netstat -ano | findstr :2000
```

### Nettoyer complètement

Pour tout supprimer (conteneurs, images, volumes) :

```bash
docker compose down -v --rmi all
```

## Structure du projet

```text
projet-xarale-main/
├── backend/
│   └── crud-xarala (1)/
│       └── crud-xarala/
│           ├── Dockerfile.dev
│           └── src/
├── frontend/
│   └── crud-front/
│       ├── Dockerfile.dev
│       └── src/
└── docker-compose.yml
```

# Smart Calculator – Multi-Agent System (JADE)

Ce projet implémente une application de calcul intelligent basée sur une architecture **multi-agents** utilisant la plateforme **JADE (Java Agent DEvelopment Framework)**.

Il contient deux agents principaux :
- **AgentUser** → Interface graphique (Swing) + envoi de requêtes de calcul  
- **AgentCalcule** → Réalise les opérations mathématiques et renvoie le résultat

---

##  Fonctionnalités

###  AgentUser
- Interface graphique moderne (Swing)
- Saisie de deux nombres
- Choix d’une opération : `+`, `-`, `*`, `/`
- Envoie automatiquement la requête à *AgentCalcule*
- Affiche le résultat reçu

###  AgentCalcule
- Reçoit un message sous la forme :  
  `a;b;op`
- Effectue l’opération demandée
- Renvoie le résultat via un message **ACL INFORM**
- Gère les erreurs (division par zéro, saisie incorrecte)

---


---

##  Lancement de l’application

### Ajouter JADE  
Assurez-vous que `jade.jar` est présent dans le projet ou ajouté au classpath de votre IDE.

###  Exécuter la classe `Main`
Elle démarre :
- La plateforme JADE
- L’interface de contrôle JADE (GUI)

  <img width="583" height="391" alt="image" src="https://github.com/user-attachments/assets/4528734c-4d22-44e8-8ff1-1705303d2e09" />

- Les deux agents (`AgentUser` et `AgentCalcule`)
  

###  Utilisation
Une fenêtre **Smart Calculator** s’affiche :
1. Saisir le premier nombre  
2. Saisir le second nombre  
3. Choisir l’opérateur  
4. Cliquer sur **Calculate**
5. 
#### Pour User 1 :
<img width="470" height="355" alt="image" src="https://github.com/user-attachments/assets/5c216a47-405a-465f-8bff-73d7f5fd5613" />

#### Pour User 2:
<img width="423" height="341" alt="image" src="https://github.com/user-attachments/assets/7e9b7c68-697f-4cb9-8d82-dc5b74516276" />






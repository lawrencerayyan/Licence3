#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

int main(int argc, char *argv[])
{
    if (argc != 2) {
        fprintf(stderr, "Utilisation : %s <nom-du-fichier-de-sortie>\n", argv[0]);
        exit(1);
    }

    const char *nomFichierSortie = argv[1];

    // Ouvrir le fichier en écriture (le créer s'il n'existe pas)
    int fd = open(nomFichierSortie, O_WRONLY | O_CREAT | O_APPEND, 0666);
    if (fd == -1) {
        perror("Erreur lors de l'ouverture du fichier de sortie");
        exit(1);
    }

    char buffer[4096];
    ssize_t lecteur;


    while ((lecteur = read(STDIN_FILENO, buffer, sizeof(buffer))) > 0) {

       
        // Écrire les données lues depuis l'entrée standard dans le fichier
        if (write(fd, buffer, lecteur) == -1) {
            perror("Erreur lors de l'écriture dans le fichier de sortie");
            close(fd);
            exit(1);
        }
    }

    // Fermer le fichier
    if (close(fd) == -1) {
        perror("Erreur lors de la fermeture du fichier de sortie");
        exit(1);
    }

    printf("Le contenu a été copié dans le fichier %s.\n", nomFichierSortie);

    return 0;
}

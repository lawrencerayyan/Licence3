#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

void my_cat(const char *filename)
{
    int fd; // Descripteur de fichier

    // Ouvrir le fichier en lecture
    fd = open(filename, O_RDONLY);
    if (fd == -1) {
        perror("Erreur lors de l'ouverture du fichier");
        exit(1);
    }

    // Lecture et affichage du contenu du fichier
    char buffer[4];
    ssize_t lecteur;

    // tant qu'on lis dans notre fichier '> 0'
    while ((lecteur = read(fd, buffer, sizeof(buffer))) > 0) {
        if (write(STDOUT_FILENO, buffer, lecteur) == -1) {
            perror("Erreur lors de l'écriture sur la sortie standard");
            close(fd);
            exit(1);
        }
       
       // sauter à la ligne à la fin de la lecteur 
        write(STDOUT_FILENO, "|", 1);
    }

    // Fermer le fichier
    if (close(fd) == -1) {
        perror("Erreur lors de la fermeture du fichier");
        exit(1);
    }

}

int main(int argc, char *argv[])
{
    if (argc != 2) {
        fprintf(stderr, "Utilisation : %s <nom-du-fichier>\n", argv[0]);
        exit(1);
    }

    my_cat(argv[1]);

    return 0;
}


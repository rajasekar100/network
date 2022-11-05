#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
char c[1024];
int main(){
	c[0]='\0';
	int sockfd, ret;
	 struct sockaddr_in serverAddr;
	int newSocket;
	struct sockaddr_in newAddr;
	socklen_t addr_size;
	int port,i=0;
	printf("port: ");
	scanf("%d",&port);
	char buffer[1024];
	char a[1024];
	pid_t childpid;

	sockfd = socket(AF_INET, SOCK_STREAM, 0);
	if(sockfd < 0){
		printf("Error in connection.\n");
		exit(1);
	}
	printf("Server Socket is created.\n");

	memset(&serverAddr, '\0', sizeof(serverAddr));
	serverAddr.sin_family = AF_INET;
	serverAddr.sin_port = htons(port);
	serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

	ret = bind(sockfd, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
	if(ret < 0){
		printf("Error in binding.\n");
		exit(1);
	}
	printf("Bind to port %d\n", port);

	if(listen(sockfd, 10) == 0){
		printf("Listening....\n");
	}else{
		printf("Error in binding.\n");
	}
	while(i<3){
		newSocket = accept(sockfd, (struct sockaddr*)&newAddr, &addr_size);
		if(newSocket < 0){
			exit(1);
		}
		printf("Connection accepted from %s:%d\n", inet_ntoa(newAddr.sin_addr), ntohs(newAddr.sin_port));
			if(i==0)
			{
				recv(newSocket, buffer, 1024, 0);
				printf("%s\n",buffer);
			}
			if(i==1)
			{
				recv(newSocket, a, 1024, 0);
				printf("%s\n",a);
			}
				if(i==2)
				{
				strcat(buffer,a);
				printf("%s : ",buffer);
				
				for (int i=0;i<strlen(buffer);i++)
					{
						c[i]=(char)((int)buffer[i]+100);
						c[i+1]='\0';
					}
					printf("Encrypted : %s\n",c);
					printf("Decrypted : ");
					for (int i=0;i<strlen(c);i++)
						printf("%c",(int)c[i]-100);
				
				printf("\n");
				send(newSocket, c,1024, 0);
				}
		i++;
	}
	close(newSocket);
	return 0;
}
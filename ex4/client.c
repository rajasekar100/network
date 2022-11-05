#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <arpa/inet.h>

void main(){
	
	 char *ip="127.0.0.1";
	 char a[100];
	 int port=5566;
	 
	 int sock;
	 struct sockaddr_in addr;
	 socklen_t addr_size;
	 char buffer[1024];
	 int n;
	 
	 sock=socket(AF_INET,SOCK_STREAM,0);
	 if(sock<0){
		 perror("[-]Socket error");
		 exit(1);
	 }
	 printf("[+]TCP server socket created.\n");
	 
	 memset(&addr,'\0',sizeof(addr));
	 addr.sin_family=AF_INET;
	 addr.sin_port=port;
	 addr.sin_addr.s_addr=inet_addr(ip);
	
	connect(sock,(struct sockaddr*)&addr,sizeof(addr));
	printf(" connected to server\n");
	
	bzero(buffer,1024);
	printf(" type here : ");
	strcpy(buffer,fgets(a,50,stdin));
	printf("client %s\n",buffer);
	send(sock,buffer,strlen(buffer),0);
	bzero(buffer,1024);
	recv(sock,buffer,sizeof(buffer),0);
	printf("server %s\n",buffer);
	close(sock);
	printf("Disconnect the server");
}

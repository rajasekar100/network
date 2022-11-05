#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
int main(int argc, char **argv)
{
	if (argc !=2)
	{
	    printf("Usage: %s <port>\n",argv[0]);
		exit(0);
	}
	
	char *ip = "127.0.0.1";
	int port = atoi(argv[1]);
	
	int sockfd;
	struct sockaddr_in addr;
	char buffer[1024];
	socklen_t addr_size;
	
	sockfd=socket(AF_INET,SOCK_DGRAM,0);
	memset(&addr,'\0',sizeof(addr));
	addr.sin_family = AF_INET;
	addr.sin_port = htons(port);
	addr.sin_addr.s_addr = inet_addr(ip);
	
	int i=0;
	int a[100];
	printf("enter the value :");
	do
	{
	scanf("%d",&a[i++]);
	}
	while (getchar()!='\n' && i<99);
	printf("\n");
	a[i];
	
	sendto(sockfd,&i,sizeof(i),0,(struct sockaddr*)&addr,sizeof(addr));
	
	sendto(sockfd,a,sizeof(a),0,(struct sockaddr*)&addr,sizeof(addr));
	printf("data sent :\n");
	for(int p=0;p<i;p++)
		printf("%d\t",a[p]);
	printf("\n");
	int m;
	addr_size=sizeof(addr);
	recvfrom(sockfd,&m,sizeof(m),0,(struct sockaddr*)&addr,&addr_size);
	int b[m];
	recvfrom(sockfd,b,sizeof(b),0,(struct sockaddr*)&addr,&addr_size);
	printf("data recv:\n");
	for(int i=0;i<m;i++)
		printf("%d\t",b[i]);
	printf("\n");
	return 0;
	
}
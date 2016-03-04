# trainnetwork
A Railway Network Interlocking Support Tool - for Newcastle University MComp Group Project

GitHub commands: 

Open a directory up in command line where you want to clone this project and then type the following commands:

1) git clone https://github.com/edwardjackchandler/trainnetwork.git TrainNetwork

HOW TO USE FILE

testNetworkFile which can be used as a template for defining a graph.

Block eg.

Block           <--- Section type
b1                <--- Block id
NA               <--- Left neighbour
b2                <--- Right neighbour
plus             <--- which track the block is on
NA               <--- Up Signal
s1                <--- Down Signal


Point eg.

Point           <--- Section type
p1                <--- Point id
b2                <--- neighbour 1
b3                <--- neighbour 2
b4                <--- neighbour 3
plus               <--- actually defines nothing... need to take it out
s

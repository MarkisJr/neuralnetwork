# Artificial Neural Network (ANN)
Created by MarkisJr. to study the applications and build process of Networks.

![Image of Code](code.png)


## Features of this Network:
- Scalable: Input values, hidden neuron and outputs can all be customised to any double value
- Lightweight: Uses little processing power and executes readily and rapidly
- Independent: Free of any external libraries and packages, can be run stand-alone
- Data sets: Allows for learning multiple tasks at the same time
- Set Batching: Sets can be handled in batches for faster processing time
- Saveable: The network can easily be saved to any file extension for modularity
- Loadable: Can load itself from any previously saved network
- Java based: Runs on the java virtual machine meaning the core network code can be ran on any platform that supports java


## Documentation
For information on how to incorperate the ANN into your code, visit the [Wiki](https://github.com/MarkisJr/neuralnetwork/wiki)...


## Current Projects:
- [MNIST database / Guess my Number Program](https://github.com/MarkisJr/neuralnetwork/tree/MNIST-database-learning) - Dedicated to learning numbers from the MNIST database as well as incorperate user interaction.
- [SPIRO Image Learning](https://github.com/MarkisJr/neuralnetwork/tree/SPIRO) - A project for procerderally generating images from a reference database of my friend Spiro


## Links and Bibliography:
- [Finn Eggers YouTube](https://www.youtube.com/channel/UCaKAU8vQzS-_e5xt7NSK3Xw) *Source of great help*
- [Neural Networks by Sebastian Lague](https://www.youtube.com/watch?v=bVQUSndDllU&list=PLFt_AvWsXl0frsCrmv4fKfZ2OQIwoUuYO) *Source of inspiration*
- [Introduction to ANN design by Daniela Kolarova](https://dzone.com/articles/designing-a-neural-network-in-java) *Understanding*


# Plans for the Future
As this network infrastructure is further developed and expanded more projects will come and improvements will be made.


### Ideas:
- Plot detector
- Image generation
- Chess ai???
- This goat does not exist???

# Training MNIST Database / Guess my Number
#### This branch is dedicated to the development of a number guessing program built on MarkisJr.'s ANN and trained on the MNIST database.

![icon](icon.png)

## Context
Initially I planned for this branch to be dedicated to purely learning the MNIST database, a collection of 60 thousand hand drawn numbers freely available to anyone wanting to train their network. Not long after I was inspired to recreate a classic ANN integrated program, taking user drawn images and testing them against data learned from the MNIST database.

## Structure
I separated the project into 4 major packages, 3 of them being distributed into the final executable jar. The mnist package, most comprised of classes downloaded from the official MNIST database website, was responsible for training the already built network package with number pictures from the database. The only originally created class in the mnist package was [Mnist.java](src/mnist/Mnist.java) which was designed to integrate with my network. The network and parser packages were simply brought over from the [master](https://github.com/MarkisJr/neuralnetwork/tree/master) branch and had already been previously designed and tested. The totally unique userpanel package was created to house the [MyFrame.java](src/userpanel/MyFrame.java) class, responsible for the user interface and handling user input.

## Downloads
The latest releases of the Guess my Number program and source code can be found [here](https://github.com/MarkisJr/neuralnetwork/releases)

![assets](assets.png)

Look for the assets tab and download the executable jar for use.

### Requirements
- [JRE / JDK] 1.8 or later

## Documentation
Information on how to use the program or create your own training set with the mnist package can be found on the [Wiki](https://github.com/MarkisJr/neuralnetwork/wiki) **(Under construction)**

## Gallery
![frame](frame.png) ![frame2](frame1.png)

## References
- The MNIST database site (source of MNIST classes): http://yann.lecun.com/exdb/mnist/

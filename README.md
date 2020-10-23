##Java binding generation for Solidity contracts
###Dependencies
1) Solidity compiler
```
brew tap ethereum/ethereum
brew install solidity
```
2) Web3j Java wrapper for the compiled contracts
```
brew tap web3j/web3j
brew install web3j
```
If you don't have gradle installed, install it:

```
brew install gradle
```

Then run 

```
gradle generateEthereumContractsBindings
```

to build Solidity code and generate Java bindings



//////////////////////////////////////////

Test deployment information

//////////////////////////////////////////

##########REQUIRES METAMASK!!!!!!!!!##########################

Clone this repo

```
git checkout -b develop
```
 
```
cd web && npm i
```

if you can not see the web folder, you may need to allow unrelated histories and pull the develop branch again, and retry

```
git pull --allow-unrelated-histories origin develop  
```


and then 

```
npm run start
```

you will be able to see the UI at http://localhost:9020




23-10-2020 Deployment Locations (Goerli Testnet)

Goerli with Uniswap market adaptor
Goerli Testnet deploy.

WETH address: 0xb4fbf271143f4fbf7b91a5ded31805e42b2208d6

CLGN Token
Contract address: 0x18b382e8ce255704d8090d3e24633492d74b66c6
Owner (can mint): CologneDAO contract (0x893ade9859be78ef78fe7f52f63138e3a44bbbea)
Can be bought at Uniswap.

EAU Token
Contract address: 0x4927073535f6baa4a10c8766b22434b656ac93d7
Owner (can mint): CologneDAO contract (0x893ade9859be78ef78fe7f52f63138e3a44bbbea)
Can be bought at Uniswap.

User Token
Contract address: 0x28c3afa4430aed6612083ac28658543dc48bb984
Owner (can mint): 0xb4Ba43ad023AB2B494070BbFf2fF7fC6D97D284b (Alexey Chernyshov)

Can be bought at Uniswap.
Market Adaptor
Uniswap Router02 as Market Adaptor: 0x7a250d5630B4cF539739dF2C5dAcb4c659F2488D

Time Provider - MOCK
NOTE: this is a mock contract, the user should update time manually
Contract address: 0xbd25b57e3a95e42b0c8133af4c41a87bba23561d

CologneDAO
Contract address: 0x893ade9859be78ef78fe7f52f63138e3a44bbbea

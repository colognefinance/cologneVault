// SPDX-License-Identifier: MIT

pragma solidity ^0.7.0;

interface IVault {
    function buy(uint amount, uint maxPrice, address to) external;

    function borrow(uint amount) external;

    function payOff(uint amount) external;

    function close() external;

    /**
      * Initiate close-out process
      * Can be called by any Ethereum wallet if the vault has breached the liquidity limit.
      * Starts Initial Liquidity Vault Auction.
      */
    function startInitialLiquidityAuction() external;

    function slash() external;

    /**
     * Mint and sell MDLY on MDLY auction to cover shortfall
     */
    function coverShortfall() external;

    // Get amount of EAU the owner can borrow now
    function getCreditLimit() external view returns (uint);

    function getTotalDebt() external view returns (uint);

    // Get total debt as principal and accrued interest charge
    function getTotalDebt(uint time) external view returns (uint);

    function getPrincipal() external view returns (uint);

    function getFees() external view returns (uint);

    // calculate fees over time
    function getFees(uint time) external view returns (uint);

    // Returns User Token price in EAU
    function getPrice() external view returns (uint);

    // Returns remaining User Token amount of vault
    function getTokenAmount() external view returns (uint);

    function getCollateralInEau() external view returns (uint);

    function getState() external view;

    event Purchase(uint amount, uint indexed price, address indexed to);
}

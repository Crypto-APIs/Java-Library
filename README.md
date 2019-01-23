Java SDK for [CryptoAPIs.io](https://cryptoapis.io/)
===========

You can get API key [here](https://dashboard.cryptoapis.io/register).
-----
## How to use

```java
final String apiKey = "your API key";
CryptoApis caClient = new CryptoApis(apiKey);
 
// Choose API connection and network
Bitcoin btc = caClient.connectToBtc(CryptoApisConstants.BITCOIN_MAINNET, EnumOptions.IncludeRawJSON.True);
 
Ethereum eth = caClient.connectToEth(CryptoApisConstants.ETHEREUM_MAINNET, EnumOptions.IncludeRawJSON.False);

Exchanges exchanges = caClient.connectToExchanges(EnumOptions.IncludeRawJSON.True);

// If EnumOptions.IncludeRawJSON is set to True, a raw json format of the response will be included in the returned object
```
 
## Ethereum examples
### Generate new address

```java
Pair<EthAddressEckPair, ApiError> response = ethAddressService.generateNewAddress();
EthAddressEckPair addressEckPair;
if (response.getKey() != null) {
    addressEckPair = response.getKey();
    
    System.out.println(addressEckPair.getAddress());
    System.out.println(addressEckPair.getPrivateKey());
    System.out.println(addressEckPair.getPublicKey());
}
```

It will print the following:
```
0x81f8debb815d29adb14c07bc5012a81f38e0b160
4f208bfb7b4b9c7a487e8f4e25726ddd87cff18555089ebb90624295cf1452bb
b920dc976a27a5d2666629b4ae58df02f243f92c092145c51715d2f083b19816234add3357628658b653cb4f3e0e70cdfac5289ccec5fe7bff5510a21f46cfea
```

### Generate new account

```java
final String password = "your password - l3tt3rs and d1g1ts";
Pair<String, ApiError> response = ethAddressService.generateAccount(password);
System.out.println(response.getKey());
System.out.println(response.getValue());
```

 If the queried transaction exists it will print the following (error object will be null):
 
```json
{ 
  "success":"keystore saved",
  "address":"0xedacf174acf303ddb8ad351d6875244f7f4d7d1e"
}
```
 
  If an error occurs it will print the following (transaction object will be null):
  
```json
{
  "meta":
    {
      "error":
        {
          "message": "Password is too weak! It should be at least 7 characters and should contain at least one letter and one digit",
          "code": 15
        }
    },
  "statusCode": 400
}
```
### Get address balance

```java
String address = "0xd6cB6744B7f2Da784c5aFd6B023D957188522198";
Pair<EthAddressBalance, ApiError> response = ethAddressService.getAddressBalance(address);
System.out.println(response.getValue());
System.out.println(response.getKey());
```

```json
{
  "chain":"ETH.mainnet",
  "address":"0xd6cB6744B7f2Da784c5aFd6B023D957188522198",
  "balance":"662.55152771818296 Ether",
  "rawJSON":""
}
```

### Get block info by number

```java
EthBlockService ethBlockService = eth.getEthBlockService();
Pair<EthBlock, ApiError> response = ethBlockService.getBlock(6123321);
System.out.println(response.getValue());
System.out.println(response.getKey());
```

```json
{ 
  "chain":"ETH.mainnet",
  "height":6123321,
  "hash":"0xdd9cda46ecc76633504931d5f7fd4bbff4c39ca3d6d173550f37789a863135b2",
  "parent_hash":"0xa19c2538a3ffa5077645dc1ecef2ef99fcbf9707b106df5536244e87b13d06bc",
  "sha3Uncles":"0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",
  "gas_used":7992049,
  "total":8071969490000000,
  "size":"5332 bytes",
  "timestamp":1533919094,
  "date":"2018-08-10 16:38:14 +UTC",
  "transactions":36,
  "difficulty":3544115802240984,
  "total_difficulty":"5917863156620322787518",
  "gas_limit":8000029,
  "nonce":"0x20f173b005ccec35",
  "mined_by":"0xea674fdde714fd979de3edf0f56aa9716b898ec8 (Ethermine) in 3 secs",
  "uncles":[],
  "extra_data":"0x65746865726d696e652d657538",
  "confirmations":736728,
  "total_fees":100689146837974998
}
```

### Get an existing transaction by a given hash:

```java
EthTransactionService ethTransactionService = eth.getEthTransactionService();
Pair<EthTransaction, ApiError> response = ethTransactionService.getTransactionByHash("0xe7abcffe85acf8e6d3186f1378d201b0857c41d300885c9c3c2f2c72afaecbcd");
System.out.println(response.getKey());
System.out.println(response.getValue());
```

If the queried transaction exists it will print the following (error object will be null):
 
```json
 {    
    "chain":"ETH.mainnet",
    "status":"0x1",
    "index":1,
    "hash":"0xe7abcffe85acf8e6d3186f1378d201b0857c41d300885c9c3c2f2c72afaecbcd",
    "value":0,
    "from":"0xf51a8cee8d3e9dc1764d2b6ef3fad973ebdb0ec5",
    "to":"0x2f141ce366a2462f02cea3d12cf93e4dca49e4fd",
    "date":"2018-12-06 10:09:38 +UTC",
    "block_hash":"0x503c138005347065a606d3d0d02b3528790227b3a17b6fd6b54ce54ed79f144a",
    "block_number":6836009,
    "gas":36657,
    "gas_price":97000000000,
    "gas_used":36657,
    "nonce":573,
    "confirmations":12,
    "input":"0xa9059cbb000000000000000000000000cfbd73a1404a2cbf956e9e506ff5006601bcd2a40000000000000000000000000000000000000000000000021d3bd55e803c0000",
    "token_transfers":[{
      "from":"0xf51a8cee8d3e9dc1764d2b6ef3fad973ebdb0ec5",
      "to":"0xcfbd73a1404a2cbf956e9e506ff5006601bcd2a4",
      "value":"39.000",
      "tokenName":"Free Coin",
      "symbol":"FREE",
      "tokenType":"ERC-20"
     }]
  }
```
 
If an error occurs it will print the following (transaction object will be null):
  
```json
  {
    "meta":
      {
        "error":
          { 
            "message": "Transaction not found",
            "code": 21
           }
       },
    "statusCode": 404
  }
```

### Create payment forwarding using an account

```java
EthPaymentService ethPaymentService = eth.getEthPaymentService();
Pair<EthPayment, ApiError> response = ethPaymentService.createPFPwd("0xedacf174acf303ddb8ad351d6875244f7f4d7d1e", "0x5dcaa1d2d8132e5bf9cf12deccfc0ceaf26a480d", 
            "https://somepoint.com", "your-password-123");
System.out.println(response.getValue());
System.out.println(response.getKey());
```

If the queried transaction exists it will print the following (error object will be null):

```json
{
  "fromAddress":"0xedacf174acf303ddb8ad351d6875244f7f4d7d1e",
  "toAddress":"0x5dcaa1d2d8132e5bf9cf12deccfc0ceaf26a480d",
  "uuid":"2474b2cb-7a8c-4d47-8764-e79c019854ea",
  "callBack":"https://somepoint.com",
  "timestamp":"2018-12-10 15:35:29.187",
  "rawJSON":""
}
```
 
If password is misspelled the following will be printed (transaction object will be null):
  
```json
  {
    "meta":
      {
        "error":
          { 
            "message": "Wrong password",
            "code": 19
           }
       },
    "statusCode": 400
  }
```
  
### Create transaction using an account 

```java
EthTransactionService ethTransactionService = eth.getEthTransactionService();
final String from = "0xc438d912235ff5facc22c502e5bd6dc1ae14a7ff";
final String to = "0x0cb1883c01377f45ee5d7448a32b5ac1709afc11";
final BigInteger gasPrice = BigInteger.valueOf(8000000000L);
final BigInteger gasLimit = BigInteger.valueOf(30000);
final String password = "your-password-123";
final BigDecimal value = BigDecimal.valueOf(0.012);
Pair<String, ApiError> response = ethTransactionService.createTxKeyStore(from, to, gasPrice, gasLimit, value, null, password);
System.out.println(response.getKey());
```  

```json
{
  "txs": "0xfebf0691cc92f67ace032897a83587c44c5b2ef437341c6cfd5dbf1f8f4c288d"
}
```

## Bitcoin examples
### Generate new address

```java
BtcAddressService btcAddressService = btc.getBtcAddressService();
Pair<BtcAddress, ApiError> response = btcAddressService.generateNewAddress();
System.out.println(response.getKey());
```

It will print the following:
```json
{
    "address":"1EXzTGyBNhnCgJrbx1Fq7vwNwSwNsHbLeV",
    "privateKey":"fe201280629f0563911dba754d05c24342bf4cbc76701d52fa90f76a3efc04de",
    "publicKey":"030c93c2792d25543c010dd198649dfb8163151c95fe47d17b2b0759009bdcd434",
    "wif":"L5jhPi836v5688kNgx3XqFSPLWMgjRfTot3AB7oBuXiPAMPSRg3X",
    "rawJSON":"{\"privateKey\":\"fe201280629f0563911dba754d05c24342bf4cbc76701d52fa90f76a3efc04de\",\"address\":\"1EXzTGyBNhnCgJrbx1Fq7vwNwSwNsHbLeV\",
      \"wif\":\"L5jhPi836v5688kNgx3XqFSPLWMgjRfTot3AB7oBuXiPAMPSRg3X\",\"publicKey\":\"030c93c2792d25543c010dd198649dfb8163151c95fe47d17b2b0759009bdcd434\"}"
}
```

### Get block info by a given hash

```java
BtcBlockService btcBlockService = btc.getBtcBlockService();
final String blockHash = "0000000000000000002e57767a22dc10c7e49117a0a15c02a88f0e9f91c18550";
Pair<BtcBlock, ApiError> response = btcBlockService.getBlock(blockHash);
System.out.println(response.getKey());
```

It will print the following:
```json
{ 
  "previousHash":"0000000000000000002299e95baaf105ad1d96d5e0ac1d69251b376855d51009",
  "tx":
    [
      "f2255ac14a56e1b48018b9e2455dc1d257045c2b6fcf277efa6f39365ea6d594",
      "72130afc01a30e2aa5216798f0d11e6b38aaf79aec94c71440edb22cf4a30b7a",
      "0053c759b110229c44301e53862951dc000d090ad1546adb6c11501ea9b0b586",
      "10772c854514dd2dc26263b65f44812631b915028620db9c4cc8c88000290844",
      "4f452e2b9ec384497c12fd908eb21e47d18f2ebed742f0d9ba250c2670ba058e",
      ...
      "a1010f3ab45381cc4aab1dbad931be8372e897003db50e4cf2b7dfbf9425f97f"
    ],
    "n_tx":2231,
    "bits":"1731d97c",
    "merkleRoot":"b56eb8352c03d5f8303876f7e6c53fc6caaa3df9c284ce6f5af5aa618144eb47",
    "confirmations":3,
    "version":1073733632,
    "nonce":3242116661,
    "difficulty":5646403851534.721,
    "chainwork":"000000000000000000000000000000000000000004550d1447ca84c281320ab6",
    "size":1217479,
    "time":"2018-12-11 08:45:04 UTC",
    "hash":"0000000000000000002e57767a22dc10c7e49117a0a15c02a88f0e9f91c18550",
    "height":553381,
    "timestamp":1544517904,
    "rawJSON":"..."
}
```

### Get transactions by index and limit

```java
BtcTransactionService btcTransactionService = btc.getBtcTransactionService();
final int blockNumber = 553381;
Map<String, String> queries = new HashMap<>();
queries.put("index", "23");
queries.put("limit", "2");

Pair<List<BtcTransaction>, ApiError> response = btcTransactionService.getTxByIdxAndLimit(blockNumber, queries);
System.out.println(response.getKey());
```

```json
[{
	"txid": "798827ad690caab3b81cd4a440891a6dd668f3c5919fa56be839b1d1b54af5bd",
	"block_hash": "0000000000000000002e57767a22dc10c7e49117a0a15c02a88f0e9f91c18550",
	"confirmations": 7,
	"version": 1,
	"block_time": 1544517904,
	"size": 405,
	"time": "2018-12-11 08:45:04 UTC",
	"vin_sz": 1,
	"hash": "f87019dff4d07670570c455dda41a73c91f2abdc6c1628864baacef82ccdc100",
	"vout_sz": 2,
	"locktime": 0,
	"timestamp": 1544517904,
	"inputs": [{
		"coinbase": false,
		"addresses": ["367f4YWz1VCFaqBqwbTrzwi2b1h2U3w1AF"],
		"prev_hash": "1d972dfb5b442973699c84869e237690ed417005d4fa118d379813935a273cc9",
		"output_value": 49191068,
		"output_index": 0,
		"vout": 0,
		"script": {
			"asm": "0020bcf9f822194145acea0f3235f4107b5bf1a91b6b9f8489f63bf79ec29b360913"
		}
	}],
	"outputs": [{
		"addresses": ["367f4YWz1VCFaqBqwbTrzwi2b1h2U3w1AF"],
		"asm": "OP_HASH160 30897cc6c9d69f6a2c2f1c651d51f22219f1a4f6 OP_EQUAL",
		"type": "scripthash",
		"value": 2671495,
		"reqsigs": 1
	}, {
		"addresses": ["3BMEXWkBJdnohnpWErfsKZ1psBos282Svp"],
		"asm": "OP_HASH160 69f37580dd01b9553792a31737d94b713f777597 OP_EQUAL",
		"type": "scripthash",
		"value": 46477773,
		"reqsigs": 1
	}]
}, {
	"txid": "8ebe7c5a3731a7be5c8d5abbc6258bfc520ac89dcec6a12166dc08f09b2bfb08",
	"block_hash": "0000000000000000002e57767a22dc10c7e49117a0a15c02a88f0e9f91c18550",
	"confirmations": 7,
	"version": 1,
	"block_time": 1544517904,
	"size": 256,
	"time": "2018-12-11 08:45:04 UTC",
	"vin_sz": 1,
	"hash": "8ebe7c5a3731a7be5c8d5abbc6258bfc520ac89dcec6a12166dc08f09b2bfb08",
	"vout_sz": 3,
	"locktime": 0,
	"timestamp": 1544517904,
	"inputs": [{
		"coinbase": false,
		"addresses": ["19hg28v3YZtLX13mrxyk6S2yyBZdnEqbZ6"],
		"prev_hash": "70abbc6040c8ebf4bcdf70e96ade46e6837af96a4e003b63ddbafd8f827a5721",
		"output_value": 9949445,
		"output_index": 1,
		"vout": 1,
		"script": {
			"asm": "30440220421fb7d8a2f58ec1b85dadbc39e9d7d02e3c5fa258a09fe941f9842e6d79349b022010d460c9bb6409acdc955f97b805c32c370fefa77f3006103f68212143e4124b[ALL] 03ce7ad2fccee78670fce2e531ca06f9634f9ae0ec80d62e7c965465b8d4394e63"
		}
	}],
	"outputs": [{
		"addresses": ["1CtcjPCPrh6zB83VJx4ze9LkcCbJFuyiaH"],
		"asm": "OP_DUP OP_HASH160 826c6c97ab8b14e3523a326b877278a0caf468dc OP_EQUALVERIFY OP_CHECKSIG",
		"type": "pubkeyhash",
		"value": 555,
		"reqsigs": 1
	}, {
		"addresses": ["19hg28v3YZtLX13mrxyk6S2yyBZdnEqbZ6"],
		"asm": "OP_DUP OP_HASH160 5f723776f0b99bdc73a0dad79853cf706ab95a2d OP_EQUALVERIFY OP_CHECKSIG",
		"type": "pubkeyhash",
		"value": 9898890,
		"reqsigs": 1
	}, {
		"asm": "OP_RETURN 6f6d6e69000000000000001f0000002eaebb3500",
		"type": "nulldata",
		"value": 0,
		"reqsigs": 0
	}]
}]
```
### Create transaction

```java
BtcTransactionService btcTransactionService = btc.getBtcTransactionService();
List<BtcTransactionBody.Inputs> inputs = new ArrayList<>();
BtcTransactionBody.Inputs btcAddOne = new BtcTransactionBody.Inputs();
btcAddOne.setAddress("1P3t6SKHuymgrs2vvgFvtsmnKen2C8gKU9");
btcAddOne.setValue(BigDecimal.valueOf(0.54));
inputs.add(btcAddOne);

BtcTransactionBody.Inputs btcAddTwo = new BtcTransactionBody.Inputs();
btcAddTwo.setAddress("1K2huCLxy9tXWc5Yn8ow6vqPGvTaCXHo5q");
btcAddTwo.setValue(BigDecimal.valueOf(1.0));
inputs.add(btcAddTwo);

List<BtcTransactionBody.Outputs> outputs = new ArrayList<>();
BtcTransactionBody.Outputs out = new BtcTransactionBody.Outputs();
out.setAddress("1EdcP2TSFsiQHNGvbbgsxgH7HfaFC54GwF");
out.setValue(BigDecimal.valueOf(1.54));

final BigDecimal fee = BigDecimal.valueOf(0.000015);
Pair<String, ApiError> response = btcTransactionService.createTx(inputs, outputs, fee);
System.out.println(response.getKey());
System.out.println(response.getValue());
```

```json
{
  "tosign": "0200000002fba29356aed5f478c9a3ca49937e1574e7437b90f1c5e68cf2806c8d25b598e20000000000ffffffff224693e22c8c442ed336c0...2dfec21c6efa27cf8bbe888ac00000000"
}
```
```
null
```

### Create wallet

```java
BtcWalletService btcWalletService = btc.getBtcWalletService();
List<String> addresses = new ArrayList<>();
addresses.add("1MfyBywPTSj9aAPr8cccCTcch71fd4vkDA");
addresses.add("1B5WsYR8m4axbmEMMifveDL2gtZjtpaFr5");
final String walletName = "demoWallet1";

Pair<BtcWallet, ApiError> response = btcWalletService.createWallet(addresses, walletName);
System.out.println(response.getKey());
```

```json
{
	"addresses": ["1MfyBywPTSj9aAPr8cccCTcch71fd4vkDA", "1B5WsYR8m4axbmEMMifveDL2gtZjtpaFr5"],
	"walletName": "demoWallet",
	"hd": false,
	"rawJSON": "..."
}
```

### Create HD wallet

```java
final String walletName = "demohdwallet";
final int addressCount = 5;
final String password = "your password - l3tt3rs and d1g1ts";

Pair<BtcHDWallet, ApiError> response = btcWalletService.createHDWallet(walletName, addressCount, password);
System.out.println(response.getKey());
```

```json
{
	"walletName": "demohdwallet",
	"addressCount": 0,
	"addresses": [{
		"path": "M/0H/0/0",
		"address": "1PMwNcWE41k9JJbzCDufdML9seg8BgcgLz"
	}, {
		"path": "M/0H/0/1",
		"address": "1CBGA1u2NR6j29Lv3dKbFTTFKrcEJq77dS"
	}, {
		"path": "M/0H/0/2",
		"address": "1Jbe7d3ynRG1r69ZJg2CwzixxA1mtB6mk4"
	}, {
		"path": "M/0H/0/3",
		"address": "1GttuBsAhesZX689JQWsuCZGdFJwRL9ohC"
	}, {
		"path": "M/0H/0/4",
		"address": "1Ez4wd8mvWciTSnscRiKDAmxUQvqm6NAQR"
	}],
	"hd": true,
	"rawJSON": "..."
}
```

### Add address to wallet
```java
final String walletName = "demoWallet";
Pair<BtcWallet, ApiError> response = btcWalletService.generateAddressBtcWallet(walletName);
System.out.println(response.getKey());
```

```json
{
	"addresses": ["1MfyBywPTSj9aAPr8cccCTcch71fd4vkDA", "1B5WsYR8m4axbmEMMifveDL2gtZjtpaFr5", "1HtxJw1PaH1F8nfoR1YxwcZCoEzuk7XZzb"],
	"walletName": "demoWallet1",
	"hd": false,
	"rawJSON": "..."
}
```

### Create Confirmed Transaction Webhook

```java
BtcWebhookService btcWebhookService = btc.getBtcWebhookService();
final String url = "https://somepoint.com";
final int confirmations = 6;
final String transaction = "a252edb1329406c7257944734cdf7139f9ca0aaccc5967b371329aad5a8745bc";

Pair<Webhook, ApiError> response = btcWebhookService.createConfirmedTxWh(url, confirmations, transaction);
System.out.println(response.getKey());
```

```json
{
	"event": "CONFIRMED_TX",
	"url": "https://somepoint.com",
	"confirmations": 6,
	"transaction": "a252edb1329406c7257944734cdf7139f9ca0aaccc5967b371329aad5a8745bc",
	"id": "41c6dec7-65d7-4b96-8c34-1aa3b0bc4568",
	"rawJSON": "..."
}
```

## Exchanges examples
### Get specific rate - ExchangeRates

```java
ExchangeRatesService exchangeRatesService = exchanges.getExchangeRatesService();
final String baseAsset = "USD";
final String quoteAsset = "BTC";

Pair<SpecificRate, ApiError> response = exchangeRatesService.getSpecificRate(baseAsset, quoteAsset, null);
System.out.println(response.getKey());
```

```json
{
	"payload": {
		"baseAsset": "USD",
		"quoteAsset": "BTC",
		"weightedMedianAveragePrice": 0,
		"medianPrice": 0.00029815047231017389,
		"weightedAveragePrice": 0.00029576383943760205,
		"timestamp": 1544530345
	},
	"rawJSON": "..."
}
```

### List all exchanges - Metadata

```java
MetadataService metadataService = exchanges.getMetadataService();
Pair<Exchange, ApiError> response = metadataService.getExchangesList();
System.out.println(response.getKey());
```

```json
{
	"meta": {
		"totalCount": 145
	},
	"payload": [{
		"exchangeId": "BINANCE",
		"name": "Binance",
		"website": "https://www.binance.com/",
		"_id": "5b1ea9d21090c200146f7362",
		"_created": 1528736210,
		"_lastModified": 1534149843
	}, {
		"exchangeId": "COINBASE",
		"name": "GDAX",
		"website": "https://www.gdax.com/",
		"_id": "5b1ea9d21090c200146f7363",
		"_created": 1528736210,
		"_lastModified": 1528736210
	}, {
		"exchangeId": "BITSTAMP",
		"name": "Bitstamp Ltd.",
		"website": "https://www.bitstamp.net/",
		"_id": "5b1ea9d21090c200146f7364",
		"_created": 1528736210,
		"_lastModified": 1528736210
	}, {
		"exchangeId": "OKCOIN_CNY",
		"name": "OKCoin CNY",
		"website": "https://www.okcoin.cn/",
		"_id": "5b1ea9d21090c200146f7365",
		"_created": 1528736210,
		"_lastModified": 1528736210
	}, 
	 ...
	 {
		"exchangeId": "COBINHOOD",
		"name": "Cobinhood",
		"website": "https://cobinhood.com/",
		"_id": "5b4e11256ab304000a106942",
		"_created": 1531842853,
		"_lastModified": 1543584287
	}],
	"rawJSON": "..."
}
```

### Get latest data - OHLCV

```java
OHLCVService ohlcvService = exchanges.getOhlcvService();
final String symbolID = "5bfc325e9c40a100014db989";
Map<String, String> params = new HashMap<>();
params.put("period", "1day");
params.put("limit", "2");

Pair<Data, ApiError> response = ohlcvService.getLatestData(symbolID, params);
System.out.println(response.getKey());
```

```json
{
	"meta": {
		"totalCount": 14
	},
	"payload": [{
		"exchange": "5b1ea9d21090c200146f7366",
		"eventType": "SPOT",
		"assetBase": "5b1ea92e584bf5002013066a",
		"assetQuote": "5b755dacd5dd99000b3d92b2",
		"timePeriodStart": 1544313600,
		"timePeriodEnd": 1544400000,
		"timeOpen": 1544370617,
		"timeClose": 1544389807,
		"priceOpen": 0.0058141699999999996,
		"priceClose": 0.00594,
		"priceLow": 0.0058141699999999996,
		"priceHigh": 0.00594,
		"volumeTraded": 74.556160640000002,
		"tradesCount": 9,
		"_id": "5c0ea33b481c570001cec0ea",
		"_created": 1544463163,
		"_lastModified": 1544515527
	}, {
		"exchange": "5b1ea9d21090c200146f7366",
		"eventType": "SPOT",
		"assetBase": "5b1ea92e584bf5002013066a",
		"assetQuote": "5b755dacd5dd99000b3d92b2",
		"timePeriodStart": 1544227200,
		"timePeriodEnd": 1544313600,
		"timeOpen": 1544228287,
		"timeClose": 1544313465,
		"priceOpen": 0.0057761399999999999,
		"priceClose": 0.0058141699999999996,
		"priceLow": 0.0057761399999999999,
		"priceHigh": 0.0058783699999999999,
		"volumeTraded": 3088.6773624800007,
		"tradesCount": 24,
		"_id": "5c0d57cc481c570001768450",
		"_created": 1544378316,
		"_lastModified": 1544395254
	}],
	"rawJSON": "..."
}
```

### Get historical data - Quotes

```java
QuotesService quotesService = exchanges.getQuotesService();
Pair<Data, ApiError> pair = quotesService.getHistoricalData("5bc08a0758e3da000142ba9d", null);
System.out.println(pair.getValue());
```

```json
{
	"meta": {
		"totalCount": 150
	},
	"payload": [{
		"exchangeId": "5bc08966a6f99f000b5968c2",
		"price": 0.0000113,
		"_created": 0,
		"_lastModified": 0
	}, {
		"exchangeId": "5bc08966a6f99f000b5968c2",
		"price": 0.0000115,
		"_created": 0,
		"_lastModified": 0
	},
	 ...
	 {
		"exchangeId": "5bc08966a6f99f000b5968c2",
		"price": 0.0000113,
		"_created": 0,
		"_lastModified": 0
	}],
	"rawJSON": "..."
}
```

### Get latest data by - Trades

```java
TradesService tradesService = exchanges.getTradesService();
final String symbolID = "5bfc325e9c40a100014db989";
Map<String, String> params = new HashMap<>();
params.put("limit", "1");

Pair<Data, ApiError> response = tradesService.getLatestDataBySymbol(symbolID, params);
System.out.println(response.getKey());
```

```json
{
	"payload": [{
		"exchangeId": "5b1ea9d21090c200146f7366",
		"baseAsset": "5b1ea92e584bf5002013066a",
		"quoteAsset": "5b755dacd5dd99000b3d92b2",
		"eventTime": 1544531920967,
		"exchangeSequenceId": "31500006",
		"tradeType": "SPOT",
		"price": 0.0057800000000000004,
		"amount": 3.74375634,
		"direction": "SELL"
	}],
	"rawJSON": "..."
}
```

### Exchanges - Services/Methods


| ExchangeRatesService | MetadataService  |   OHLCVService    |   QuotesService   |     TradesService     |             
| -------------------- | ---------------- | ----------------- | ----------------- | --------------------- |  
| getSpecificRate      | getExchangesList | getPeriodsList    | getLatestData     | getLatestData         |
| getCurrentRate       | getAssetsList    | getLatestData     | getHistoricalData | getHistoricalData     | 
|                      | getSymbolsList   | getHistoricalData |                   | getLatestDataBySymbol |


### Ethereum - Services/Methods


|   AddressService   | BlockchainService |  BlockService  | ContractService | PaymentService |  TokenService   | TransactionService  |    WebhookService     |               
| ------------------ | ----------------- | -------------- | --------------- | -------------- | --------------- | ------------------- | --------------------- |  
| generateNewAddress | getBlockchainInfo | getBlock x2    | estimateGasSC   | createPFPvt    | getTokenBalance | getTx x3            | createNewBlockWh      |
| getAddressInfo     |                   | getLatestBlock | deploySC        | createPFPwd    | transferPvt     | getTxByIdxAndLimit  | createAddressWh       | 
| getAddressBalance  |                   |                |                 | deletePF       | transferPwd     | createTxKeyStore    | createConfirmedTxWh   |
| generateAccount    |                   |                |                 | listPayments   |                 | createTxPvt         | createUnconfirmedTxWh |
| getTxsByAddress    |                   |                |                 | listPHistory   |                 | getRawTxBody        | createTokenWh         |
|                    |                   |                |                 |                |                 | estimateGasLimit    | createTxPollWh        |
|                    |                   |                |                 |                |                 | broadcastSignedTx   | deleteWebhook         |
|                    |                   |                |                 |                |                 | createTxPvtAll      | listWebhooks          |
|                    |                   |                |                 |                |                 | createTxKeyStoreAll |                       |
|                    |                   |                |                 |                |                 | getPendingTxs       |                       |
|                    |                   |                |                 |                |                 | getQueuedTxs        |                       |
    
                                
### Bitcoin - Services/Methods


|   AddressService   | BlockchainService |  BlockService  | PaymentService | TransactionService |       WalletService        |    WebhookService     |               
| ------------------ | ----------------- | -------------- | -------------- | ------------------ | -------------------------- | --------------------- |  
| generateNewAddress | getBlockchainInfo | getBlock x2    | createPFPwd    | getTx x2           | createWallet               | createNewBlockWh      |
| getAddressInfo     |                   | getLatestBlock | deletePF       | getTxByIdxAndLimit | createHDWallet             | createAddressWh       | 
| getTxsByAddress    |                   |                | listPayments   | traceTxs           | listWallets                | createConfirmedTxWh   |
|                    |                   |                |                | getHistoricalTxs   | listHDWallets              | createUnconfirmedTxWh |
|                    |                   |                |                | createTx           | getWallet                  | deleteWebhook         |
|                    |                   |                |                | sendTx             | getHDWallet                | listWebhooks          |
|                    |                   |                |                | decodeTx           | addAddressToWallet         |                       |
|                    |                   |                |                | getUnconfirmedTxs  | generateAddressBtcWallet   |                       |
|                    |                   |                |                |                    | generateAddressBtcHDWallet |                       |
|                    |                   |                |                |                    | removeAddress              |                       |
|                    |                   |                |                |                    | deleteBtcWallet            |                       |
|                    |                   |                |                |                    | deleteHDBtcWallet          |                       |
     
     
### Query Params


|     Params      |
| --------------- |     
| index           |
| limit           |
| period          |
| timeperiodstart |
| timeperiodend   |  
| timestamp       |
| timestart       |
| timeend         |
| txs-included    |
| datetime-from   |
| datetime-to     |
                         
Check out the documentation of CryptoAPIs for more information.

## License

MIT

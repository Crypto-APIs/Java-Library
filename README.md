# ⚠️ SDK Deprecation Notice

> **Status:** Archived & Deprecated  
> **Effective Date:** 1st April, 2025

---

This SDK has been officially **deprecated** and is no longer actively maintained or updated.

To give you more flexibility, we recommend generating your own SDK using our official **OpenAPI specification**, available for download from the Crypto APIs Developer Portal:

👉 [https://developers.cryptoapis.io/](https://developers.cryptoapis.io/)

---

## 💡 Recommended Approach

You can easily generate SDKs in most major programming languages using tools such as:

- [OpenAPI Generator](https://openapi-generator.tech/)
- [Swagger Codegen](https://swagger.io/tools/swagger-codegen/)

Simply use our OpenAPI JSON to create SDKs tailored to your specific needs.

---

This repository will remain available for reference but is no longer supported.

For questions, please contact us via our official support channels.

---

_Thank you for your understanding and for building with Crypto APIs!_

Java SDK for [CryptoAPIs.io](https://cryptoapis.io/)
===========

You can get API key [here](https://dashboard.cryptoapis.io/register).
-----
## Getting started

Add the relevant dependency to your project:

### Maven
Java 8:

```java
<dependency>
    <groupId>io.cryptoapis</groupId>
    <artifactId>cryptoapis-java-client</artifactId>
    <version>1.3.0</version>
</dependency>
```
### Gradle
Java 8:

```java
compile("io.cryptoapis:cryptoapis-java-client:1.3.0")
```

## How to use

```java
final String apiKey = "your API key";
CryptoApis caClient = new CryptoApis(apiKey);

// Choose API connection and network
final Exchanges exchanges = caClient.connectToExchanges();

final Ethereum eth = caClient.connectToEth(CryptoApisConstants.ETHEREUM_MAINNET);

final Bitcoin btc = caClient.connectToBtc(CryptoApisConstants.MAINNET);

final Bitcoin_Cash bch = caClient.connectToBch(CryptoApisConstants.MAINNET);

final Litecoin ltc = caClient.connectToLtc(CryptoApisConstants.MAINNET);

final Dogecoin doge = caClient.connectToDoge(CryptoApisConstants.MAINNET);
```
 
## Ethereum examples
### Generate new address

```java
final AddressService ethAddressService = eth.getAddressService();

ApiResponse res = ethAddressService.generateNewAddress();
System.out.println(res.getResponse());
```

It will print the following:
```json
{
  "payload": {
      "address": "0xb8e51b75a8ab6995b2e11938fed3b05837ec5fa2",
      "privateKey": "5d06b11d276e169d2fa2ced94d327902ee954ff47e31bcbf0aa4957a829a0aed",
      "publicKey": "303727940fca8f9b1f580908246aa980f9ee2aa9ecc379cbaf6a80f0050c1a2cdd7a467c1e564a2e272fb5b1958320ca11ca79b9cdfaf2bd74e6decd2592daa8"
  }
}
```

### Generate new account

```java
final String password = "your password - l3tt3rs and d1g1ts";
ApiResponse res = ethAddressService.generateAccount(password);
System.out.println(res.getResponse());
```

 If will print the following:
 
```json
{
    "payload": {
        "success": "keystore saved",
        "address": "0x14eefa06e6f617ba9df050d7f9415e5bd0f11d34"
    }
}
```
 
  If an error occurs, it will print the following (e.g. your password is weak):
  
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
final String address = "0xd6cB6744B7f2Da784c5aFd6B023D957188522198";
ApiResponse res = ethAddressService.getAddressBalance(address);
System.out.println(res.getResponse());
```

```json
{
	"payload": {
		"chain": "ETH.mainnet",
		"address": "0xd6cb6744b7f2da784c5afd6b023d957188522198",
		"balance": "1152.66525530903683",
		"txs_count": 146347,
		"from": 47476,
		"to": 98871
	}
}
```

### Get block info by number

```java
final BlockService ethBlockService = eth.getBlockService();
ApiResponse res = ethBlockService.getBlock(6123321);
System.out.println(res.getResponse());
```

```json
{
    "payload": {
        "chain": "ETH.mainnet",
        "height": 6123321,
        "hash": "0xdd9cda46ecc76633504931d5f7fd4bbff4c39ca3d6d173550f37789a863135b2",
        "parent_hash": "0xa19c2538a3ffa5077645dc1ecef2ef99fcbf9707b106df5536244e87b13d06bc",
        "sha3Uncles": "0x1dcc4de8dec75d7aab85b567b6ccd41ad312451b948a7413f0a142fd40d49347",
        "gas_used": "7992049",
        "size": "5332 bytes",
        "timestamp": 1533919094,
        "date": "2018-08-10 16:38:14 +UTC",
        "transactions": 36,
        "difficulty": "3544115802240984",
        "total_difficulty": "5917863156620322787518",
        "gas_limit": "8000029",
        "nonce": "0x20f173b005ccec35",
        "mined_by": "0xea674fdde714fd979de3edf0f56aa9716b898ec8 (Ethermine) in 3 secs",
        "uncles": [],
        "extra_data": "0x65746865726d696e652d657538",
        "confirmations": 1455815,
        "total_fees": "100689146837974998"
    }
}
```

### Get an existing transaction by a given hash:

```java
final TransactionService ethTransactionService = eth.getTransactionService();
ApiResponse res = ethTransactionService.getTx("0xe7abcffe85acf8e6d3186f1378d201b0857c41d300885c9c3c2f2c72afaecbcd");
System.out.println(res.getResponse());
```

If the queried transaction exists it will print the following:
 
```json
 {
     "payload": {
         "chain": "ETH.mainnet",
         "status": "0x1",
         "index": 1,
         "hash": "0xe7abcffe85acf8e6d3186f1378d201b0857c41d300885c9c3c2f2c72afaecbcd",
         "value": 0,
         "from": "0xf51a8cee8d3e9dc1764d2b6ef3fad973ebdb0ec5",
         "to": "0x2f141ce366a2462f02cea3d12cf93e4dca49e4fd",
         "date": "2018-12-06 10:09:38 +UTC",
         "block_hash": "0x503c138005347065a606d3d0d02b3528790227b3a17b6fd6b54ce54ed79f144a",
         "block_number": 6836009,
         "gas": "36657",
         "gas_price": "97000000000",
         "gas_used": "36657",
         "nonce": 573,
         "confirmations": 743129,
         "token_transfers": [
             {
                 "from": "0xf51a8cee8d3e9dc1764d2b6ef3fad973ebdb0ec5",
                 "to": "0xcfbd73a1404a2cbf956e9e506ff5006601bcd2a4",
                 "value": "39.000",
                 "tokenName": "Free Coin",
                 "symbol": "FREE",
                 "tokenType": "ERC-20"
             }
         ],
         "input": "0xa9059cbb000000000000000000000000cfbd73a1404a2cbf956e9e506ff5006601bcd2a40000000000000000000000000000000000000000000000021d3bd55e803c0000"
     }
 }
```
 
If transaction is not found, the following message will be printed:
  
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
final PaymentService ethPaymentService = eth.getPaymentService();
ApiResponse res = ethPaymentService.createPFPwd("0x7857af2143cb06ddc1dab5d7844c9402e89717cb", "0x4ab47e7b0204d6b3bf0e956db14e63142b9b5ab8", 
            "https://somepoint.com", "your-password-123", 6, 11000000000L, 21000);
System.out.println(res.getResponse());
```

The response will be as follows:

```json
{
    "payload": {
        "uuid": "7f0c4666-af1f-44a3-b5f5-1a3c2b904283",
        "from": "0x7857af2143cb06ddc1dab5d7844c9402e89717cb",
        "to": "0x4ab47e7b0204d6b3bf0e956db14e63142b9b5ab8",
        "confirmations": 2,
        "callback": "http://somepoint.com",
        "gasPrice": "11000000000",
        "gasLimit": "21000"
    }
}
```
 
If password is misspelled the following will be printed:
  
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
  
### Create, sign and send a transaction to the blockchain 

```java
final TransactionService ethTransactionService = eth.getTransactionService();
final String from = "0xc438d912235ff5facc22c502e5bd6dc1ae14a7ff";
final String to = "0x0cb1883c01377f45ee5d7448a32b5ac1709afc11";
final BigInteger gasPrice = BigInteger.valueOf(8000000000L);
final BigInteger gasLimit = BigInteger.valueOf(30000);
final String password = "your-password-123";
final BigDecimal value = BigDecimal.valueOf(0.012);
final hexData = "0x123"; //  a hexadecimal value
ApiResponse res = ethTransactionService.createTxKeyStore(from, to, gasPrice, gasLimit, value, hexData, password);
System.out.println(res.getResponse());
```  

```json
{
    "payload": {
        "hex": "0xfebf0691cc92f67ace032897a83587c44c5b2ef437341c6cfd5dbf1f8f4c288d"
    }
}
```

## Bitcoin, Litecoin and Bitcoin Cash examples
### Generate new address

```java
final AddressService btcAddressService = btc.getAddressService();
ApiResponse res = btcAddressService.generateNewAddress();
System.out.println(res.getResponse());
```

It will print the following:
```json
{
    "payload": {
        "privateKey": "2edfe66bed8a17e58cd51a3da72f63d0a6b0ca8775d237772f88e42c27f992e0",
        "publicKey": "037039ac5fac75ab9eb8bdbde8a9ba2dc5d7576b711e69a241a5e2dfdbc71647a5",
        "address": "1P2CyS7TPqHPQeQExDXSK25hPs2jCk3c46",
        "wif": "Kxnq2zxvNatWtHbQzmakRdH21YNozFjD141s2mKpo21C6sVwa16e"
    }
}
```

### Get block info by a given hash

```java
final BlockService bchBlockService = bch.getBlockService();
final String blockHash = "000000000000000002dbd3af3c654b77ea277238b02df23d93ca6b3e4eedb295";
ApiResponse res = bchBlockService.getBlock(blockHash);
System.out.println(res.getResponse());
```

It will print the following:
```json
{
    "payload": {
        "hash": "000000000000000002dbd3af3c654b77ea277238b02df23d93ca6b3e4eedb295",
        "size": 974324,
        "height": 419398,
        "version": 536870912,
        "versionHex": "20000000",
        "merkleroot": "67c988f47c40ef54c87d530be54b9a2ee2d575462beda0c920e23f44b2c24ff4",
        "time": "2016-07-05 13:07:56 UTC",
        "mediantime": "2016-07-05 12:15:24 UTC",
        "nonce": 3430795024,
        "bits": "180526fd",
        "difficulty": 213398925331.3239,
        "chainwork": "0000000000000000000000000000000000000000001eb413e634b72bd107f569",
        "previousblockhash": "0000000000000000039b2f5bf42183b25299c9ffb4a99111ea35819a701e84a2",
        "nextblockhash": "00000000000000000016e54e255f112a015c15f92dfd30f7f5259df6729280c7",
        "transactions": 1768,
        "tx": [
            "48d0c5e7d7f1004e4fda1ad02529b024d59e8d663486d9b9cad4fe92d3f9d63e",
            "2046a3a6affe4886c53060f5f5637b9369f188046b2ea2d3172ca99a0bcb7137",
            ...
            "14f18a93af7ffb3dacec0d419e68efa8a436068390691a5d1f54baedbab55a00"
        ],
        "confirmations": 0,
        "timestamp": 1467724076
    }
}
```

### Get transactions by index and limit

```java
final TransactionService ltcTransactionService = ltc.getTransactionService();
final int blockNumber = 553381;
Map<String, String> queries = new HashMap<>();
queries.put("index", "23");
queries.put("limit", "2");

ApiResponse res = ltcTransactionService.getTxByIdxAndLimit(blockNumber, queries);
System.out.println(res.getResponse());
```

```json
{
	"payload": [{
		"txid": "cacb2282399e250bbbe60fbb49e62e60f0bd123563ff853730e038be8e4a116f",
		"hash": "cacb2282399e250bbbe60fbb49e62e60f0bd123563ff853730e038be8e4a116f",
		"index": 23,
		"version": 1,
		"size": 3190,
		"vsize": 3190,
		"locktime": 0,
		"time": "2014-04-22 03:48:34 UTC",
		"blockhash": "3334d66be09560433bda17f335c4c6d0017be0bc18a887f9596249c2123d3e15",
		"blockheight": 553381,
		"blocktime": "2014-04-22 03:48:34 UTC",
		"timestamp": 1398138514,
		"confirmations": 1055211,
		"txins": [{
			"txout": "ee9f6d825607b6ceb1482139a43394ef9b640383f12b809a046a9dae62f7bd5b",
			"vout": 1,
			"amount": "0.0139",
			"addresses": ["LeqLW7KgkYfaaz2DTSbzh2cZCm8LbUU1NX"],
			"script": {
				"asm": "304402204d6dde1a7f0db805eccafa7a401f4a6b47d6b144c02a40de8dc77d4743f9691e0220689c55f64912b9cb17387d49ac33326bb8200c81325c0ca257078b1123d40f7a[ALL] 036b503d5b9d37d295aa710179686696a26097c5ded86779d517d99d139e3268e8",
				"hex": "47304402204d6dde1a7f0db805eccafa7a401f4a6b47d6b144c02a40de8dc77d4743f9691e0220689c55f64912b9cb17387d49ac33326bb8200c81325c0ca257078b1123d40f7a0121036b503d5b9d37d295aa710179686696a26097c5ded86779d517d99d139e3268e8"
			},
			"votype": "pubkeyhash"
		  },
		 ...
		 {
			"txout": "72229f239e1e07de267310dafca1546d046b2ccb7a8ed97166377e0a029bc7a9",
			"vout": 1,
			"amount": "1.9639",
			"addresses": ["LhUwWyncweni85nat8g6b7YVKfNuJsxi3p"],
			"script": {
				"asm": "304502201dbaed3e5d9424df9f2c9fded04ff26462dabd94670bdbb343ae81eeaa1c4c8802210087d58c21bca9d8da42f498951afbb43aab1e97247111e13de5caacde994b4317[ALL] 033e300857f2b1751a96fb8dd2262b5a4cd5fd34958a485d5bc33e30fe0a66e6c0",
				"hex": "48304502201dbaed3e5d9424df9f2c9fded04ff26462dabd94670bdbb343ae81eeaa1c4c8802210087d58c21bca9d8da42f498951afbb43aab1e97247111e13de5caacde994b43170121033e300857f2b1751a96fb8dd2262b5a4cd5fd34958a485d5bc33e30fe0a66e6c0"
			},
			"votype": "pubkeyhash"
		}],
		"txouts": [{
			"amount": "40",
			"type": "pubkeyhash",
			"spent": true,
			"addresses": ["LTi2gMpBpV73VeiY3CiSqbkQ5QSeUP2GPC"],
			"script": {
				"asm": "OP_DUP OP_HASH160 5d1056fa8321e89a47d2718f659c320ea1b9851d OP_EQUALVERIFY OP_CHECKSIG",
				"hex": "76a9145d1056fa8321e89a47d2718f659c320ea1b9851d88ac",
				"reqsigs": 1
			}
		}, {
			"amount": "0.01000521",
			"type": "pubkeyhash",
			"spent": true,
			"addresses": ["LTKqrfL2kjgzBhMJ5JBcSfLY8Mm77FdLEg"],
			"script": {
				"asm": "OP_DUP OP_HASH160 58de278f79671418afb6dedca037ed28c8e88301 OP_EQUALVERIFY OP_CHECKSIG",
				"hex": "76a91458de278f79671418afb6dedca037ed28c8e8830188ac",
				"reqsigs": 1
			}
		}]
	}, {
		"txid": "79f9ff7ea230c4bd2a214cf37b577773a2bfe5b6827ca86258a7aa7c538f00ee",
		"hash": "79f9ff7ea230c4bd2a214cf37b577773a2bfe5b6827ca86258a7aa7c538f00ee",
		"index": 24,
		"version": 1,
		"size": 967,
		"vsize": 967,
		"locktime": 0,
		"time": "2014-04-22 03:48:34 UTC",
		"blockhash": "3334d66be09560433bda17f335c4c6d0017be0bc18a887f9596249c2123d3e15",
		"blockheight": 553381,
		"blocktime": "2014-04-22 03:48:34 UTC",
		"timestamp": 1398138514,
		"confirmations": 1055211,
		"txins": [{
			"txout": "61feafc03b11107e86c3f8e6699a75b87b766d7e222eb9664c1738d05eba74e2",
			"vout": 1,
			"amount": "0.00015",
			"addresses": ["LXwpBzPnMoMAcv4FKnZYXDZE1KkrYC4Dy2"],
			"script": {
				"asm": "304502206225d41fb743b045e3c8733525a4ae1c6c58a1461f690f722bc2655bdc8fb3e602210081ad463354073dacd8ed0d1da3f418258197508e68508fe54cf27d9299ea348f[ALL] 028b42eb7e6a01fc2cbd1c4a44a212cbd097e2084d70dab167f469e1356cb5116a",
				"hex": "48304502206225d41fb743b045e3c8733525a4ae1c6c58a1461f690f722bc2655bdc8fb3e602210081ad463354073dacd8ed0d1da3f418258197508e68508fe54cf27d9299ea348f0121028b42eb7e6a01fc2cbd1c4a44a212cbd097e2084d70dab167f469e1356cb5116a"
			},
			"votype": "pubkeyhash"
		}, 
		 ...
		 {
			"txout": "cacb2282399e250bbbe60fbb49e62e60f0bd123563ff853730e038be8e4a116f",
			"vout": 1,
			"amount": "0.01000521",
			"addresses": ["LTKqrfL2kjgzBhMJ5JBcSfLY8Mm77FdLEg"],
			"script": {
				"asm": "3045022057d48cdd2ba3eaf0c5b6fb04768170e50830f001ec2f476a0cf5e490e2673450022100b9a057699d913a2215b147a27e1c61b6b2d196a6a2aa7b5247f4e75259d51108[ALL] 02f442706d23bf3400d96ba54c9ab54a7ac0a15a4a969b5756ca72588c6c9c9963",
				"hex": "483045022057d48cdd2ba3eaf0c5b6fb04768170e50830f001ec2f476a0cf5e490e2673450022100b9a057699d913a2215b147a27e1c61b6b2d196a6a2aa7b5247f4e75259d51108012102f442706d23bf3400d96ba54c9ab54a7ac0a15a4a969b5756ca72588c6c9c9963"
			},
			"votype": "pubkeyhash"
		}],
		"txouts": [{
			"amount": "0.05",
			"type": "pubkeyhash",
			"spent": true,
			"addresses": ["LbonesQ7pYjfeX8SCnihs4Y53BRYkuxor7"],
			"script": {
				"asm": "OP_DUP OP_HASH160 b5e8223d2f49348c99f0b8a9971ccedd9a6fb3da OP_EQUALVERIFY OP_CHECKSIG",
				"hex": "76a914b5e8223d2f49348c99f0b8a9971ccedd9a6fb3da88ac",
				"reqsigs": 1
			}
		}, {
			"amount": "0.00014521",
			"type": "pubkeyhash",
			"spent": true,
			"addresses": ["LQgaa4x3Dh8bs1ZdB1LgrWWTAiwWK7QD4U"],
			"script": {
				"asm": "OP_DUP OP_HASH160 3be1c0903a68529c07f048b474c000208e82b619 OP_EQUALVERIFY OP_CHECKSIG",
				"hex": "76a9143be1c0903a68529c07f048b474c000208e82b61988ac",
				"reqsigs": 1
			}
		}]
	}],
	"meta": {
		"totalCount": 25,
		"index": 23,
		"limit": 2,
		"results": 2
	}
}
```
### Create, sign and send a transaction to the blockchain
```java
final TransactionService btcTransactionService = btc.getTransactionService();
CreateTransaction ct = new CreateTransaction();

List<CreateTransaction.Inputs> inputs = new ArrayList<>();
CreateTransaction.Inputs input1 = new CreateTransaction.Inputs();
input1.setAddress("mtFYoSowT3i649wnBDYjCjewenh8AuofQb");
input1.setValue(0.0003);
inputs.add(input1);

CreateTransaction.Inputs input2 = new CreateTransaction.Inputs();
input2.setAddress("mn6GtNFRPwXtW7xJqH8Afck7FbVoRi6NF1");
input2.setValue(0.001);
inputs.add(input2);

ct.setInputs(inputs);

List<CreateTransaction.Outputs> outputs = new ArrayList<>();
CreateTransaction.Outputs output = new CreateTransaction.Outputs();
output.setAddress("mrnWMV41vXivQX9yiY9ACSK5uPo3TfJdv9");
output.setValue(0.0013);
outputs.add(output);

ct.setOutputs(outputs);

//setting fee value is mandatory, however setting fee address is optional
CreateTransaction.Fee fee = new CreateTransaction.Fee();
fee.setValue(0.00145);
fee.setAddress("mmskWH7hG9CJNzb16JaVFJyWdgAwcVEAkz");
ct.setFee(fee);

final List<String> wifs = new ArrayList<String>(){{
   add("cUGddnJmuzfzpWXNwt1SRnQ8GMqZdQ1vg8BtwjG8f275pvExPzaX");
   add("cSEjySAREyai8eQhgoqixzmxCeSP8QtbwHxptL8ijofg68ZMjoud");
   add("cPo4XXgsnkVdg93GqR8M1iCDGk6Tgywk5gng4rSpoMVzpQx13WmA");
}};

ApiResponse res = btcTransactionService.newTx(ct, wifs);
System.out.println(res.getResponse());
```

```json
{
	"payload": {
		"txid": "fa4ea6eb1947ae94040fd416afd8262016e6f8c0e69bae94c1dd9a4a80a92b7e"
	}
}
```

### Create wallet

```java
final WalletService ltcWalletService = ltc.getWalletService();
final List<String> addresses = new ArrayList<String>(){{
         add("LLrxRzrNVxyVQ2DjuMoCNEMN2YM2nkwr1K");
         add("LiLGYuVLV4HExGYizwZEjTpTG2apN4or8M");
         add("LP3LrpZDjCDhysQarq4STG97sH8a8Yf748");
      }};
final String walletName = "demoWallet";
ApiResponse res = ltcWalletService.createWallet(addresses, walletName);
System.out.println(res.getResponse());
```

```json
{
	"payload": {
		"walletName": "demoWallet",
		"addresses": ["LLrxRzrNVxyVQ2DjuMoCNEMN2YM2nkwr1K", "LiLGYuVLV4HExGYizwZEjTpTG2apN4or8M", "LP3LrpZDjCDhysQarq4STG97sH8a8Yf748"],
		"hd": false
	}
}
```

### Create HD wallet

```java
final WalletService bchWalletService = bch.getWalletService();
final String walletName = "demoHDWallet";
final int addressCount = 5;
final String password = "your password - l3tt3rs and d1g1ts";
ApiResponse res = bchWalletService.createHDWallet(walletName, addressCount, password);
System.out.println(res.getResponse());
```

```json
{
	"payload": {
		"walletName": "demoHDWallet",
		"addresses": [{
			"address": "bitcoincash:qz5c8fpj25hz8wmnqy2k04enwvd937k89ur7hcft6e",
			"legacy": "1GTJu2NexADzFcFhvqozHczVP7h1uh1WzN",
			"path": "M/0H/0/0"
		}, {
			"address": "bitcoincash:qr6rvvvh4u8v9vaq4kuf7rh00r8smu78cy3sycwmdq",
			"legacy": "1PGGoFdvDQQmEZuTuwNQFo7vwfWhpAi2Ji",
			"path": "M/0H/0/1"
		}, {
			"address": "bitcoincash:qzr6tqax9hhr77x9a2ttffc6ktgal2rtwyprcsha9s",
			"legacy": "1DNEW2gwyL2XnTLgCcsYt1kM9KMpFz3ni7",
			"path": "M/0H/0/2"
		}, {
			"address": "bitcoincash:qzhcldlhnk6s9gjmgx6nkypxrwfjslu7r5yph59svl",
			"legacy": "1H1HRKHVS6pMHCcbYDTVegwyFgj77Lz27U",
			"path": "M/0H/0/3"
		}, {
			"address": "bitcoincash:qpkv6ufumrka40ca8c8px8kftnj48tch7u9afwd65e",
			"legacy": "1AvJ66sw83hC9v1KZBG7rTYNxpxKwrsdTN",
			"path": "M/0H/0/4"
		}],
		"hd": true
	}
}
```

### Add address to wallet
```java
final String walletName = "demoWallet";
ApiResponse res = ltcWalletService.generateAddressWallet(walletName);
System.out.println(res.getResponse());
```

```json
{
	"payload": {
		"wallet_info": {
			"walletName": "demoWallet",
			"addresses": ["LLrxRzrNVxyVQ2DjuMoCNEMN2YM2nkwr1K", "LiLGYuVLV4HExGYizwZEjTpTG2apN4or8M", "LP3LrpZDjCDhysQarq4STG97sH8a8Yf748", "LfX7oHnE37ch1KYmn8RFbtfNbFWnngmYW7"],
			"hd": false
		},
		"address_info": {
			"privateKey": "4442839f4f27fff266ce22bcfb1edd8135792a52d086649874007bcc8eb23f71",
			"publicKey": "02737644477a499f487d07359a35e6dda11afc0f656ec5cbe693ae02060b91ef06",
			"address": "LfX7oHnE37ch1KYmn8RFbtfNbFWnngmYW7",
			"wif": "T5LfYR3EKoidySNmsaALbpWPQDJZyAFAsZXP4B9Gd19kMsukJC1v"
		}
	}
}
```

### Create Transaction Using HDWallet

```java
final Bitcoin_Cash bch = ca.connectToBch("testnet");
final TransactionService transactionService = bch.getTransactionService();

final String walletName = "demohdwallet";
final String password = "password";

List<CreateTransaction.Outputs> outputs = new ArrayList<>();
CreateTransaction.Outputs output = new CreateTransaction.Outputs();
output.setAddress("my4TmbbhJCLJB9q1eHUHQWJfbbJoYdLwtE");
output.setValue(0.003);
outputs.add(output);

CreateTransaction.Fee fee = new CreateTransaction.Fee();
fee.setValue(0.00023141);

ApiResponse response = transactionService.newTxWithHDWallet(walletName, password, null, outputs, fee, 0);
System.out.println(response.getResponse());
```

```json
{
  "payload": { 
       "txid":"1a6bffc375ecd14eb8fa85308baae86a68278cb12d78a2aedbb014c10fb080a1"
   }
}
```

### Create Confirmed Transaction Webhook

```java
final WebhookService btcWebhookService = btc.getWebhookService();
final String url = "https://somepoint.com";
final int confirmations = 6;
final String tx="909d545c8ca26a754118f88939ff38a76dad2a06fdff6117c4b6b029f6330625";

ApiResponse res = btcWebhookService.createConfirmedTxWh(url, tx, confirmations);
System.out.println(res.getResponse());
```

```json
{
	"payload": {
		"uuid": "de6c3898-b6a0-44be-8ff8-f2beb83dd09f",
		"event": "CONFIRMED_TX",
		"transaction": "909d545c8ca26a754118f88939ff38a76dad2a06fdff6117c4b6b029f6330625",
		"confirmations": 6,
		"url": "https://somepoint.com"
	}
}
```

### Get blockchain info

```java
final BlockchainService service = doge.getBlockchainService();
final ApiResponse res = service.getBlockchainInfo();

System.out.println(res.getResponse());
```
```json
{
    "payload": {
        "difficulty": 6329995.2247605,
        "headers": 2745874,
        "chain": "main",
        "chainWork": "0000000000000000000000000000000000000000000001eedd07ae5c113b9d92",
        "blocks": 2745874,
        "bestBlockHash": "c895d63eb2f7e306bc6d1df85e085d13ed83509dc70ba9ce3f84564461bbd234",
        "currency": "DOGE",
        "transactions": 12687555,
        "verificationProgress": 0.99999946
    }
}
```

## Exchanges examples
### Get specific rate - ExchangeRates

```java
final ExchangeRatesService exchangeRatesService = exchanges.getExchangeRatesService();
final String baseAsset = "USD";
final String quoteAsset = "BTC";

ApiResponse res = exchangeRatesService.getSpecificRate(baseAsset, quoteAsset, null);
System.out.println(res.getResponse());
```

```json
{
	"payload": {
		"weightedAveragePrice": 0.0001908939768173263,
		"medianPrice": 0.0001914660979263376,
		"weightedMedianAveragePrice": 0,
		"timestamp": 1555484749,
		"baseAsset": "USD",
		"quoteAsset": "BTC"
	}
}
```

### List all exchanges - Metadata

```java
final MetadataService metadataService = exchanges.getMetadataService();
final Map<String, String> params = new HashMap<String, String>() {{
    put("limit", "5");
}};

ApiResponse res = metadataService.getExchangesList(params);
System.out.println(res.getResponse());
```

```json
{
	"meta": {
		"totalCount": 25,
		"index": 0,
		"limit": 5,
		"results": 5
	},
	"payload": [{
		"exchangeId": "BINANCE",
		"name": "Binance",
		"website": "https:\/\/www.binance.com\/",
		"_id": "5b1ea9d21090c200146f7362"
	}, {
		"exchangeId": "COINBASE",
		"name": "GDAX",
		"website": "https:\/\/www.gdax.com\/",
		"_id": "5b1ea9d21090c200146f7363"
	}, {
		"exchangeId": "BITSTAMP",
		"name": "Bitstamp Ltd.",
		"website": "https:\/\/www.bitstamp.net\/",
		"_id": "5b1ea9d21090c200146f7364"
	}, {
		"exchangeId": "BITTREX",
		"name": "Bittrex",
		"website": "https:\/\/bittrex.com\/",
		"_id": "5b1ea9d21090c200146f7366"
	}, {
		"exchangeId": "POLONIEX",
		"name": "POLONIEX",
		"website": "https:\/\/poloniex.com\/",
		"_id": "5b1ea9d21090c200146f7367"
	}]
}
```

### Get latest data - OHLCV

```java
final OHLCVService ohlcvService = exchanges.getOhlcvService();
final String symbolID = "5bfc325e9c40a100014db989";
final Map<String, String> params = new HashMap<String, String>(){{
            put("period", "1day");
            put("limit", "2");
        }};

ApiResponse res = ohlcvService.getLatestData(symbolID, params);
System.out.println(res.getResponse());
```

```json
{
	"meta": {
		"totalCount": 23
	},
	"payload": [{
		"exchange": "5b1ea9d21090c200146f7366",
		"eventType": "SPOT",
		"assetBase": "5b1ea92e584bf5002013066a",
		"assetQuote": "5b755dacd5dd99000b3d92b2",
		"timePeriodStart": 1545091200,
		"timePeriodEnd": 1545177600,
		"timeOpen": 1545095064,
		"timeClose": 1545105503,
		"priceOpen": 0.00536766,
		"priceClose": 0.00533631,
		"priceLow": 0.00533631,
		"priceHigh": 0.00540231,
		"volumeTraded": 1713.40194932,
		"tradesCount": 10,
		"_id": "5c1a11c0481c5700015eaeff",
		"_created": 1545212352,
		"_lastModified": 1545218854
	}, {
		"exchange": "5b1ea9d21090c200146f7366",
		"eventType": "SPOT",
		"assetBase": "5b1ea92e584bf5002013066a",
		"assetQuote": "5b755dacd5dd99000b3d92b2",
		"timePeriodStart": 1545004800,
		"timePeriodEnd": 1545091200,
		"timeOpen": 1545025630,
		"timeClose": 1545073697,
		"priceOpen": 0.00547147,
		"priceClose": 0.00539047,
		"priceLow": 0.00535,
		"priceHigh": 0.00548921,
		"volumeTraded": 64723.87729499,
		"tradesCount": 260,
		"_id": "5c195cc2481c57000121f344",
		"_created": 1545166018,
		"_lastModified": 1545198267
	}]
}
```

### Get historical data - Quotes

```java
final QuotesService quotesService = exchanges.getQuotesService();
final Map<String, String> params = new HashMap<String, String>(){{
       put("limit", "5");
    }};

ApiResponse res = quotesService.getHistoricalData("5bfc325c9c40a100014db8ff", params);
System.out.println(res.getResponse());
```

```json
{
	"meta": {
		"totalCount": 2124,
		"index": 0,
		"limit": 5,
		"results": 5
	},
	"payload": [{
		"exchangeSymbol": "etheur",
		"exchangeId": "5b1ea9d21090c200146f7364",
		"price": 147.53,
		"orderId": "N\/A",
		"tradeType": "SPOT",
		"baseAsset": "5b755dacd5dd99000b3d92b2",
		"quoteAsset": "5b1ea92e584bf5002013061a",
		"direction": "ASKS",
		"amount": 0.64306,
		"eventTime": 1555488613000
	}, {
		"exchangeSymbol": "etheur",
		"exchangeId": "5b1ea9d21090c200146f7364",
		"price": 147.04,
		"orderId": "N\/A",
		"tradeType": "SPOT",
		"baseAsset": "5b755dacd5dd99000b3d92b2",
		"quoteAsset": "5b1ea92e584bf5002013061a",
		"direction": "BIDS",
		"amount": 11.985,
		"eventTime": 1555488406000
	}, {
		"exchangeSymbol": "etheur",
		"exchangeId": "5b1ea9d21090c200146f7364",
		"price": 147.63,
		"orderId": "N\/A",
		"tradeType": "SPOT",
		"baseAsset": "5b755dacd5dd99000b3d92b2",
		"quoteAsset": "5b1ea92e584bf5002013061a",
		"direction": "ASKS",
		"amount": 327.0426884,
		"eventTime": 1555488406000
	}, {
		"exchangeSymbol": "etheur",
		"exchangeId": "5b1ea9d21090c200146f7364",
		"price": 147.62,
		"orderId": "N\/A",
		"tradeType": "SPOT",
		"baseAsset": "5b755dacd5dd99000b3d92b2",
		"quoteAsset": "5b1ea92e584bf5002013061a",
		"direction": "ASKS",
		"amount": 0.64306,
		"eventTime": 1555488406000
	}, {
		"exchangeSymbol": "etheur",
		"exchangeId": "5b1ea9d21090c200146f7364",
		"price": 147.76,
		"orderId": "N\/A",
		"tradeType": "SPOT",
		"baseAsset": "5b755dacd5dd99000b3d92b2",
		"quoteAsset": "5b1ea92e584bf5002013061a",
		"direction": "ASKS",
		"amount": 23.76,
		"eventTime": 1555488406000
	}]
}
```

### Get latest data by - Trades

```java
final TradesService tradesService = exchanges.getTradesService();
final String symbolID = "5bfc32a29c40a100014dc5f6";
final Map<String, String> params = new HashMap<String, String>() {{
            put("limit", "1");
        }};

ApiResponse res = tradesService.getLatestDataBySymbol(symbolID, params);
System.out.println(res.getResponse());
```

```json
{
	"meta": {
		"totalCount": 170,
		"index": 0,
		"limit": 1,
		"results": 1
	},
	"payload": [{
		"exchangeId": "5b4e131b6ab304000a106945",
		"exchangeSequenceId": "dc5d8abc-cb3b-43a2-9350-c983ef368c09",
		"eventTime": 1555488112732,
		"baseAsset": "5b1ea92e584bf50020130845",
		"quoteAsset": "5b755dacd5dd99000b3d92b2",
		"price": 2.679e-5,
		"amount": 14142.4,
		"direction": "SELL",
		"tradeType": "SPOT"
	}]
}
```

### Exchanges - Services/Methods


| ExchangeRatesService | MetadataService  |   OHLCVService    |   QuotesService   |     TradesService     |   OrderBookService    |            
| -------------------- | ---------------- | ----------------- | ----------------- | --------------------- | --------------------- |
| getSpecificRate      | getExchangesList | getPeriodsList    | getLatestData     | getLatestData         | getSnapshotBySymbolId |
| getCurrentRate       | getAssetsList    | getLatestData     | getHistoricalData | getHistoricalData     |                       |
|                      | getSymbolsList   | getHistoricalData |                   | getLatestDataBySymbol |                       |


### Ethereum, Ethereum Classic - Services/Methods


|   AddressService   | BlockchainService |  BlockService  | ContractService |  PaymentService  |       TokenService              | TransactionService  |    WebhookService     |               
| ------------------ | ----------------- | -------------- | --------------- | ---------------- | ------------------------------- | ------------------- | --------------------- |  
| getAddressInfo     | getBlockchainInfo | getBlock x2    | estimateGasSC   | createPFPvt      | getTokenBalance                 | getTx x3            | createNewBlockWh      |
| generateNewAddress |                   | getLatestBlock | deploySC        | createPFPwd      | transferPvt                     | getPendingTxs       | createAddressWh       | 
| generateAccount    |                   |                |                 | deletePF         | transferPwd                     | getQueuedTxs        | createConfirmedTxWh   |
| getTxsByAddress    |                   |                |                 | listPayments     | getTokensByAddress (Ethereum)   | getTxByIdxAndLimit  | createTokenWh         |
| getNonce           |                   |                |                 | listPastPayments | getTokenTxsByAddress (Ethereum) | createTxKeyStore    | createTXPoolWh        |
|                    |                   |                |                 |                  | getTotalSupplyAndDecimals       | createTxKeyStoreAll | deleteWebhook         |
|                    |                   |                |                 |                  | getAllTokens (Ethereum)         | createTxPvt         | listWebhooks          |
|                    |                   |                |                 |                  |                                 | createTxPvtAll      |                       |
|                    |                   |                |                 |                  |                                 | getRawTxBody        |                       |
|                    |                   |                |                 |                  |                                 | estimateGasLimit    |                       |
|                    |                   |                |                 |                  |                                 | broadcastSignedTx   |                       |
|                    |                   |                |                 |                  |                                 | getGasFees          |                       |
|                    |                   |                |                 |                  |                                 | getBasicTx          |                       |
    
                                
### Bitcoin, Litecoin, Bitcoin_Cash, Dogecoin - Services/Methods


|     AddressService     | BlockchainService |  BlockService  |  PaymentService  | TransactionService     |       WalletService     |    WebhookService     |               
| ---------------------- | ----------------- | -------------- | ---------------- | ------------------     | ----------------------- | --------------------- |  
| getAddressInfo         | getBlockchainInfo | getBlock x2    | createPFPwd      | getTxByHash            | createWallet            | createNewBlockWh      |
| getMultisigAddressInfo |                   | getLatestBlock | deletePF         | getTxByBlock x2        | createHDWallet          | createAddressWh       | 
| generateNewAddress     |                   |                | listPayments     | getUnconfirmedTxs      | listWallets             | createConfirmedTxWh   |
| getTxsByAddress        |                   |                | listPastPayments | decodeTx               | listHDWallets           | deleteWebhook         |
|                        |                   |                |                  | createTx               | getWallet               | listWebhooks          |
|                        |                   |                |                  | signTx                 | getHDWallet             | deleteAllWebhooks     |
|                        |                   |                |                  | sendTx                 | addAddressToWallet      |                       |
|                        |                   |                |                  | newTx                  | generateAddressWallet   |                       |
|                        |                   |                |                  | getFees                | generateAddressHDWallet |                       |
|                        |                   |                |                  | newTxWithHDWallet      | removeAddress           |                       |
|                        |                   |                |                  | refundTx               | deleteWallet            |                       |
|                        |                   |                |                  | transactionSize        | deleteHDWallet          |                       |
|                        |                   |                |                  | transactionSizeWithFee | createExtendedKey       |                       |
|                        |                   |                |                  | getBasicTxByHash       | getXpubReceiveAddresses |                       |
|                        |                   |                |                  | getRawTxByHash         | getXpubChangeAddresses  |                       |

### Bitcoin / Omni Layer


|     AddressService     |  NodeInfoService  |       TransactionService        |               
| ---------------------- | ----------------- | --------------                  | 
| getAddress             | getNodeInfo       | createTransaction x2            | 
| getAddressTransactions |                   | signTransaction                 |
|                        |                   | sendTransaction                 |
|                        |                   | createSignAndSendTransaction x2 | 
|                        |                   | createHDWalletTransaction x2    |                  
|                        |                   | getTransactionById              |                  
|                        |                   | getTransactionsByBlockHeight x2 |                  
|                        |                   | getTransactionsByPropertyId x2  |                  
|                        |                   | getUnconfirmedTransactions  x2  |                  
|                        |                   |                                 |

### Zilliqa

|     AddressService     | BlockchainService    | TransactionService  |  BlockService  |               
| ---------------------- | -------------------- | ------------------  | -------------- |
| getAddressInfo         | getBlockchainInfo    | getTx x3            | getBlock x2    |
| getTxsByAddress        |                      | getTxByIdxAndLimit  | getLatestBlock |
| generateNewAddress     |                      |                     |                |
|                        |                      |                     |                |

     
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
                         
Check out the documentation of CryptoAPIs for more information.

## License

MIT

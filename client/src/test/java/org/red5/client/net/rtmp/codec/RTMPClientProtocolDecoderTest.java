package org.red5.client.net.rtmp.codec;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.mina.core.buffer.IoBuffer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.red5.client.net.rtmp.RTMPClientConnManager;
import org.red5.io.utils.IOUtils;
import org.red5.server.api.Red5;
import org.red5.server.net.protocol.RTMPDecodeState;
import org.red5.server.net.rtmp.RTMPConnection;
import org.red5.server.net.rtmp.RTMPMinaConnection;
import org.red5.server.net.rtmp.codec.RTMP;
import org.red5.server.net.rtmp.codec.RTMPProtocolDecoder;
import org.red5.server.net.rtmp.event.AudioData;
import org.red5.server.net.rtmp.event.Notify;
import org.red5.server.net.rtmp.message.Packet;

public class RTMPClientProtocolDecoderTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    //    @Test
    //    public void testDecodeBuffer() {
    //        //byte[] buf = IOUtils.hexStringToByteArray("02000000000004050000000000989680420000000000050600989680024200000000000604000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000");
    //        byte[] buf = IOUtils.hexStringToByteArray("000000000000008e140d0000000200086f6e537461747573000000000000000000050300056c6576656c0200056572726f720004636f64650200104e657453747265616d2e4661696c6564000b6465736372697074696f6e0200174261642064617461206f6e206368616e6e656c3a203634000764657461696c730200076e6f");
    //
    //        RTMPConnection conn = RTMPConnManager.getInstance().createConnection(RTMPMinaConnection.class);
    //        conn.setStateCode(RTMP.STATE_CONNECTED);
    //
    //        RTMPClientProtocolDecoder decoder = new RTMPClientProtocolDecoder();
    //        decoder.decodeBuffer(conn, IoBuffer.wrap(buf));
    //    }

    //    @Test
    //    public void testDecodeBufferYugeTimestamp() {
    //        byte[] buf = IOUtils.hexStringToByteArray("05ffffff0096a7090100000001000f4a120000878a9c9f1398d7046fddb077f532aa9ff889fff2f2b43be1e8253eeeaab3b9557d436a20eda523de6a9fb74466dee9a22e285700e0eea9fd57c6b7ea2fa7d5ef228a071446a3a36fdbfc4d9047e8edb0514031f5eede7fdeaacfbcc9f959d63014bb15e4c9be9e91a52a201c06920dc288f9eb9dedb07b9bb2cfecce0ebedd512fc76a2c8f7bf7d4f37e0a553208ea10b77d8c658b60f0fc3faf874ab773f2e48a4756f847bc6aa8f01ca07790784ef7cb8a9a57f44aac6af2ed5783cf2caef34cbfc1d7d4c1198f5f16765a23d532db47728ebd2d7ffdf08c3af81db9f50d7f6d68779e934753f5baa044fc0ce296fddf4f6d8ab331bbc82377e235fe5f0ea67e657be0f6cfb3ec9b355f30468a546daa7546eb6da983a73728adcbf69b92ddd1d4a06c44c534753f71529f5f9f8bee6628fff93c05b3f5b1175497cdc57046fd053ba1f2cbc6f9635db83dfabc1dde7e51dc47fad8651f2dbbfc58bd436c0e944d97077e6bebf608c19bf5e72f844dcfb63a614f1bf2ac97de96403b6f0fbe1eee5ecdbee6677d918f7201c6364e50608132be153739147845515508eaa88f599e0392b14747da77918b7b6f7bcee7effb2fd428ff95fae774477c17bfc536cc9546e2b9fcb265c9771999f6f55b60d0bf3ba99955976724616d513d2fb1a83ac1df8743ac3f053314ef39b6b5b93e3d8c7d5f942b1edefd81e1e7c3d1d7766c97ca3aa2285a6b63a557bf5d581a7418eb6c9ebf9556cb954ff64cadc52a14fe732cd93321f8356a227060ff40b8f3dbb3f3c3dd83bbbbaa68eb0fc2c5378077cceee8f740c8ea296b924bbb4bad90d47def07bf9bb102a6e88cabfb68ef37f1adbbe7bb7b66cb628df673a06ee7875725f08f6317ed538f994ba9f666a851076dc1d755e363ccade3dd47766cdc112596fc14dc4fdfd8a47bdcf270cdebdcc1dd99555931bd0517ad11ae49eaa64b8d76c3920c2a9b3ea079f11a0f3fbad27c1ef55a751e1ef1ede3db40c5f5b335aa3b11f40ef799e4beaa200444fb047bf8af8d5f5fb5cfabdaa47954629f08cc0efc010f8db3aa9ad9cfa6c9c537da23c5acf7bd0be34012f7a3cf630a68f55e2a556d5367a4d4db7f3f00e4ad8043f49dd1d26c8ddfdb3d9cb7decdcbe1dd6f47b87defd664e5912c0389e7f8aea754a77d35aa3c0097d11a51d5924dacb1f97d444ef35a4aabfe2e745f6d56d5940eb1e9bb3b3747579bb7189a0d0813de045ec8aa6ec962af7f7d7578c0f40ee829656f03385f2a63d64eb783b60085806b4476b074acfbf251d0ee8f3447c8cf552bc1e65b00de7672fb5d0bb6c51b97b3be9367edd9f1d0f37f739b8d6cd8f719b93eaea8c02e3b1dafbf9e55d11b732fa2d8f84f291d5a0a6a0430757d73297ddeca9f666034106f6f503ce8e957be3aadfb9db9b40ccf41dd57e51b79c501000f4aa79d260ec1572fe0eae58c6455ea3b55dade37002dea29c5623a847dd51e038a55fecb00efaf798aa28fdb7cf74c50ac44fd976d9737944799c1d2bb6d98a4457bfffed7f7fdcf707a071a8ad50e877db36f6f54643bfe078460611f2c85d621e58d4fd557fc80727945d91ef87e8f55a81180c6c9b14fb7e3bff670796d11b747469f24cc68446a7af14453155a3aaa7b6cd66f2a9009ff03bbdfe45407673d93365117df6ea8ea8e8eadb34f40fa8295b96df5b7b66e551e1ddbfacdf491b0cdd3f936f77718b6a9efbf137aabc6d44b3d54dc3af3c0359df2adc5116ecd914feee2d7823ea973e3fd64aa475b3fd11e0f32511bf9074dd50a6dffb3d9dbf7c3a8d96f24dfeb5724abd9fdf6700e4fe51e5503bf864cd8a7aac78a62bf4bb00c01d9e11e76ede2a52b29f986c8b4ba07bb8d29cfc6a7759e81795562a5723a0e4922de9d97d7ec2bb74773277e3a52d4967c1a2fe8d613cfa8cba3d9739fb33dfac0ebaabff1dd51d3ef85f794d1e6fa4dfd9cbb7bb681d11ee48c0efcdcb36064f700e4a225a07475e022a3b38a2aca7253102a2a8daa366e65aa3d245377b9ffc56d0f6ffdae81cdc03d272b397edc1dc900e0e94a850a78da8f4a7e1da99681b6a5519ececdc6a7fd7feb201b03b6e1d82e378ad5453b6c5b38a73e3d03f8224513a3d51647bffe5feb1eefa0eb996583a5704667ba3cffc0d704673ed9f6e7fc067b7ffef9a915da3db4757f83acea8d1db9d144e60f878a251dabd11aedfd1e35726f2d5bd5a068409e33ead9bc1157faa0342246b7bf2fcdaa5b92034107080a4bfcdbced8dce59b6c9ba234572cbfe29be36f9e1d81e51efe35c1d49baa7927f7c3aeef7b37aa6866f6fe66b60792379ec03badcf01cbf916b315e004c2651d629b25f682935b1d41d2ab6359740d49901a1027de68f747b8a076ddbdd50a14f171d797848f0deb2a557bb9629fc4ea37ff6f1501ebaabcc0ec327dbfb60efaba652dca3abef5dbf9ffdf6eaab3629f83410707bf937bb3ddaa6d54df95fc0ec1d028ecb1581dddc83a87dfff6dd1d6177ec6b6fc75fbb7fbe99cfef13fea89da19c24ed11555c0607f19aa53cc6d4f3f4bb78a4477c2a8dbebc9f98a2d1d08dfaa6df7fffba3be77cc7e2b538e7dec1181827cc9c53b954cf5dda235cf6a81d6e641d3e227aea89e5337b33e23fda1ea9d677dda236fd4eabf06729f8a6660eef2019f73142867dbeff58f26a75f032ce7fd3aa7230ad6b7047ccc98076d1d52f57d7c56a84df1eef99d5327a66f7446ef5565052da0d041bf2abedd8a201ae7beae29a23cc8a947540f40ddb65aa370dc87f8a73629fdb6f3db7b681dee3540f08fbde6e3dff707bef7b9d6e4b997d76aa060e1014aad9aaa5fc0685f5e62a53db1402937780a59759c5bca6faf3df6a79cf7d91b6b7683020b54c501000f4a64d57c115b8af3bdcf7d4653f0f07b40ecfefa66aa63d2fa8888b6451b9bb96fcf40e29e2a6fbe116d1d353e3d8d4aa26a8532c9a23c3d1fbcd538ca88ad4afd5423d03bf117823df5d0689fa1aab1e5b83d11f6d1d29ef94fd7f6f80e35399f57c86ffd4f73544eecd9ff7e5fc11ac03f65dbbf032dfd469c7bd37ff7bea846932b73a3a92766eabaaacd92798879ea4632b1b5535bd6728e87db1b5718e5e3a1b36e4fdd8dcc0516cfb4a477d5364f0eed99ca23005418eeb5c4fe9760ed8c9d039fb079a22ddbbc73fd529ffdb6cb6df37f02d68f262f9c6b479f00b87f4533f32d66829b7f777b9ee4ecb3c232b59cf84adc9ecb97fecde28b1446cbfa3a6eccdcc2f0340d0bf3ff846117979e9f1187726b765cbd53ef29d1dfe32016ffcbec8c40426a457772c55b7c82c9d5fca2ab0cdd3b3cc29c8a3a3dbcffab52c55dff39228f411f98f7688b201c0517ae81fe5b9f9ec0607f3544035b7c060f40e71afcd97ca259bb39bb325c03136766405239ef13a3bf373d9267846b3947b14efea8e6cb5559966290cdfcbeef9476e28e5e37bec1dfbb7414b237d03987a093a9fb1ffd57bff7a72a86447523bf51ecc6e834207056fb3ffc52de5d6b608c3a112daa3797d15f5453d0900e373018383e7bfebf1da9ec93075ef2b1d7fbe53329effdb9dfd117dba3b536c51a3b9554f0eac99cfa997de38f76ec83b55ad81e5fd3de6a7f0154a3667d4b75d0bee55006fd235d67fa92f3d6db54794e7ff7f8015273146db940bcd11c46579bfbfbef01a89f91b00b87113b39b54a9de593077d2edbef33beed1155c3affd6874a6795727d47bde54af5affd4c8a3bb95b83ad1d86707e034a2ab6ae5c523d1d4d57bea072c91a6e64b2457c0c9ef5076a679a644757ba07340e64f8f545edc1db4ab47601705c6beaacf66aa5566e6b363392f77ca60f22b11fe0d0be3a7fdb2fe0ec44d1d8f7b31466f87558673faa798f776fd6e2985dc6a0f14ecdbf1edbd654fa0ea6e5d3eff14c2f51796ea8df4fb76d57d11ef629f6efc75822caf79552aaf6fc772ee6c53ec6f7f571165df7760343fcf84b3f1a6c46e4aa5a9d1d5feddd1efe452a24cdf2b3f0765503a82335e9672700dccf42fce75a63fd97ef75558a80e4bbf53967a740d28e29e29cf3331bdc9af7b681497b7077bb27f6455978a477eba3def2d351315c83cdbbc5338a58bd532d1dabf8ebf2e7b62bef4fb7296fd140efaaba3daa7aa80cf3ed019cefff660043e13ec72cc8a74458df25fcee726557cbeaa3dc8f725179ddee7a8e8743b11186b1ad11aaa8a395d0b60307fbbffd97ca7fef7e483bffee7d9e644848f1ffc9f1ecefd373de53b3eda4d6655767d9c7b9f3778b01bbbb2ff3aaf40daaa23efed2e11bd92b9f1bffe88cdeaa9b77f37957dd1d794794aaabf8f3e076feec501000f4a5b40e0eecc11ef847d1de2be08f545d6bce719ff631535cdccb6db40cd543de012579d0c9de8f15f7156e4e8eae7b25ec1eb11bfc9eb9bef123c4d11a313b7339797d7a073f2dfdcfab6f01a40294e375546c458066a89314b53ca6e81bfff2f7bf7c64e48076b4a14c938a6af6a9fdc031eb65fe923dd7933f3fb5b8a14fe6411afdaa3cbf95bbf51f1e453c2f00983eaa039d4de56ae65039fd1d4bec8d6c1dcad7ee866e6d5f6b565f2a03cda7c9edd57ededf5fb3db643ef83b03798a7d3aa5408fa0567b38a75afee7acdaf7fc52b51d46bdcf0ee818976c11d401de01f1d7bcf81dec6cbfda3b11a64a06a5c57251db4a73b14fb990337b30f37f35611fc057d67f5bc03797fbe6bf9148342fd0bba3dcd1dcbdff57f777445a3c53ff5f88fe1d4f00438c8d60ea67f446f7ea9f46d05d1d4c1e4f550a15d742df7981d450afb3e3d559900ec031fcdf5dc540700df35d023a11325e555bc8ae816c1e67fa23767bfcbc50f8983d9b83aeb7b15ca3bb9cf2897f3f8aa4bee6b5ef438f82adaac035e11ec51d54b5ea8faa523d996dbc6bed805c262a55b77fe11bf7ada6aaf6a990bdab3db147f36500a6f5783db7aa9428db8de01f95388d79976888d1f88a220eb73d8caace6f9b56a2e5032ab074a3a23b9e65fcb727847a3a93c0a5f4b3d315e357e3b9b6e0ef3d69e87e6f55cf4927e73f479fcccb1af66ff25bb3f334f3e1bf8d0ef5aefd56caad8a22cdccaa764b544dc819bd0ebddb2aafee8ebc3a69802d364fe58d58a72aaf3dfb9475ff0e9951155ea7edbe93ff51cb9ebc6b01a0837dfff97dc59529914adfb67c799cb73915abbda3a3713e93077976d1d46fe07e7b8a04653aa2f740aa8879e2ef3ddd503bd600b7947bfe6e8eaaaf4c513ba71edea24557f3366dceaaf51d4f08d1b9bcb446eb061f810ef9bc579262699cbac5bb77f8c27f78328c51d1155ffffe7c147c93928304f8a7ddf566b6a7f0cc7e46af846a0740e81bad0307fa3b537f1bfd11274eb58eb7e2301f96e7ace5577534dde6d4ff1d2a527217d625a3df338dd96f3cd79468eb1a1eee6b9ef3231f6e81ce0e87b6a8e58a9401c97f7806ef255833ff5162951d8d65edff94f444aa54eaf075f83a537c19bc8ea8f22903939fcb8a94e6d4a3af5cff578d8342051ba3bb1a805e7476afc3d11a81df81dcfaa8c608aaa1f89fff140f540efb414b64a23c6e7f954eb3bb37a016eb1abffdf7673648ab88edf88d9cb688c06fb0029efd1ae45a0199f973962d5683b52a95fa55219bd754b5db7bf54bdf5e28664be03a23f010f047ae89f59725d1e499075d2f03bcd1e35352e2b8d804bec80655a966cf450c88e3daae4de55c0edff2dd3d17d98a228927dbf4d6a29f6779bc9aa736295301a182ff11a01ec11aaab55f2df5b557e49eb3d92814594c6cf3ec501000f4a2dbd8dcff7c224500764e551cea86d5fff281cbbeb032ff97a3a8a55778ca8ef32f875bbd99cf6dd937e01718c8f5995b9d9eceef7992d67db27b6df2bfe3e17f172e694f47554fa4b324f779e035b7b8c678f365baca8f7a36dd96291d60ef73d373719a3aa010ef281fb2fe30232a576dd8a2b0d8ebb3bcc5006e5af7bf828afc18403b73de2e62a85f1b9d9f52be99850313eaa31b66780f298b411bbd4fff7d41d839477c9147bfd539fb7474a766b324b636a770e3f0ebfc9dce6c927f236a5b537f237147aa81d81c52e841d67447503b8a4bee59a3d97b8b0e875f1d88ed005bc77b26d052b5f1ea8037229ef6028affd4776df8f1b8012f7753b2fc46c53b336fa457ce6e4518b56a7b6834304eb95b039fbd523b983d03d556e35e665f7e95034204f9377546679a517b558eee4ea8a07e62ab8c64db7d1d13dcce8ec0c56eddbb8074181059eb8aeedefadb7f70fbdfaab51c9e038d51dee7b046f665ecaa9aefd9d051be1b3760f79d96d9aadbb24ea81e88b266d9547e64ffe064f75953fbea73f806548e98dfc8d6d513dd4a193e77414ca392ea9f5b8a3147ff00ba8ad363d534025f7be1dca07aa857246e6c940946366f877ebb187c101d823b77fbd51c56d71479b0530eb7db00e64a19b46fe0eb99362ad9b98d5c9c9228dd9a8b4fc36d684582381d9ecf627d1d7ff54818513d1bb0fbdda4b9bbd039bef6f945fde8f7d3fcfedbd9d8dfa4bd740fbaa35ba0779efdd665ea883dd0313a9d55f8043ea8cfdb67146ea7ff9ae497267a37e4d00ef81a0047962cf8292411e29b6f7b62bcb8238f25e511d41f8df0303f707bddf29676ff1a8a014cd7bc9c79375cf3f1ec5e666c1ecada8f4f2bbf6447a3d8a720efa71efde9b6a8b6db00d7231c0609f074a15d1ef8782354aa8f3ae4cdb83bee41d29a9714a8f29dfd9a3c03deabc741343ab3f55f145df88e060757674181fcefa4b267315b9a3c079b914028f3ac58c4cb7fee29e646f3d643cebb782301bf2ffa3dc113157a5b014be99f11fb0750328cf0ea4b97d40dfee974b548ea36236d823377b69e7bad65b6f9ef64aa0743bbfb5be0e969ecb1578d437d459e9bfcedea611a720e95d523bcfcbecccd39ff148ec479c11d4cab88ad4fde029778bc0088203b6645b7dffe29dc1d7f6b176d9f92fc7b144be73daa9cecff954999b00efb2df48226eea996b3769d84e8eb8b7e720f4be28d51efc53cdb75577ffec6948043e1eedbc67fa3af67ef6b5aca81e2bb92551de487e0ec503bb327f78d6f37010d8b3feccf37c51387de64f08bb7fab2957aa632a7fd0203d5555377c0d0814188b2700d01dcee2f9fe2afb39e50a1528fefb95efeddd03ad829b628573b2dda23d6d47eb1ecf77ce81e4cf7bd36d9e6d88a4bb935529ba3da3d1d6ef734e3de7f199efdca3b1d2c501000f4a8bd9ef3199b93fb33382361f819f51b65cfde5f49997f1bdeb4af340e08b3973ce7c11b3b7a07a2be089447bddcc50aed1ec0609f16af87d9e5325529aaa6a4ffa6ec9ba226a86b2e0e9547bd68eaf340e0f67fdffcf6679a9d9c50d5edb2451e876233f57a0c1fedacd654b6a478234b544cfcd00b7c3e28f26ec57beb9fc4d271bcf67f2f7d996d73ed51db366b50145df37152a1d323dee0f72c93b6fabdd94c05131c1d5e34d629cbeb15715ff66a81df076e7bb319c1d59740d7d5376291e5c1db1e5768eb00dda19bcdaadbc91becf4c927e5b66b43bb41497fe503becf866fff8dee7b32f7c07332628039c1de71430a6eca79ef14756e030309ef6f9befc0ccb1bac4b83c05270fbb8f07bd51eea9c53bd4f55ede28e377db7d9fcc8197ffd56f925b24d52a3ff49ef71911ab37bd00881cff2552ab581192ddad450aa51e7b6829b87ffe79a67c93f9ff72453bcd55714cc919d1d4f065fec4df8ed50f24c807ff5a83ad8a36a81114a8fcc53b879ee91554cbcf6cbb077ca3d523d60778232e233fffbe9e2e577767fb94760a5a3bdf5626a9bed96db7286529da0a7fd5119dd11b5451de4955c9f50df167c36089dd667c74077fd503d1e46ea8bedb92f15c52010f7ea23c971afc9e6ff73f1bc55fa3d51e11b2eca2329fbe31528c57a3b9e66cfd92c1eb4a2ab53d57b732629f88faf7fff8753aa314a91f01f1dde72db6a8cf48075adcd6541f749ea056a96b5481dcaa44796777aa5adffbd3d701a0fd8a53f52d4fff9eaa77f9a2264627aa8a05c7aa714be267e6ad9558efdf9facb7b9a3da06875bbff0342fbfff9b6d55f99faa0461d725060df3739b72333f00e9e7f9b34790789ad579e91ac11fb71b4fe98d9f7ba45192767d6f2853b20fee0e9bdfa996ec8d4c87646eefa4c55288debffaffbdb2028fca5a5293823be1077fdea95383af2bcf747583b9df5bcfc556e7d44dd3bffb1550323b926cfec8c88ea00c5543acf269d742ad145ff6a855baaa7acc54a54d90763bcff475718f1eff9c1d707bbe1de339ed92ff601c9273a3d976b47defad5513968129f9e82288eae830509154d1d37bb03376533eaadfff315b322b9a053c23caa479bc54c670ec2c62376edf31477bfbcefa5a22f2fa4b3aa2804badef55ec6ff3ca2b736676dbfac555e51078ab7678e42c51f9e53cada811a6e8f00d88f4777667e7a714dbfba19ffc1e7dab7fbff45377907bed54ab7a235baca8efc7475f05ce2a664060811aec532fb65517ec4d03aae8ef321f864dd117d54567d54dfcdeaa57338a879a06228aa4328850a608d9f6a49d96772a96acb1281dbefe67f0338ff6f7fb773e0667f6029ee4cfee29c6ef2b53c0172507bcfde089dc53edbb6c1ecc1ed9ffdcbf5ffc5471d154e01ccbe513aac0f550db78ae4dea9feaabbb6293ec501000f4af82b33117d4d4bff5053f73ca7144bcd6cb8ebb4d11445998dffdf96b3144be823e6e6681c3702cfd56f31b9608f6c53fb0757fdd9b9fb2d5114e8f1cfbe1da9f503bbf1d7fbbd82388d9f543ae777f83c51d0ce1fd797e0a2e816537d47ba3ce29e36a14ece4c9c3eebc9e53f9676e6ecdcfc1dfc793555ac6eaed8f7e016f83f31547bea354e5cff3dbfe45167556aa4d6fb92d8e8ab40e5de08f8dcaaff9557d4d039eb67d9dbfad5ed7c0e8ef91abc5577806846ef315f5465557feff4468e7efddc573b3cb7eaa64798affc5091b9ada8aacfc3767a29a91857ea3a031755f268293f4763b96ba247567365bf6b2adce8880715fbdaa3f7fc5794dbdfef00ecfffd3ca6d54cedb7362eac756f27999a61f95cb40e371ae5f4c8ab5ba3bbe403abaaacbff81f7c3dcdb14dee4e714c8226abfef6d517773f3bdfe3e22cfeb623cf7355452a277b68ef38dc57eda3b520d0ff7fea6f8f54ce6b23caa8149f67faa1663dcc0ce0f9dfc80a9a077e5d7f1bfd5bc5db67fdfeea8c9fec8193e37f9664a3d926e7e5dc11b5368f502adf7402dfc44fdbcd9f9014ffff4757de8978a00e60d22a5ccb73f354faf75befe56b7ecaa1efbcbc3f16c5cdded031f2ea3a8894fb7dfbfe6f94ca4ef4a4471e365ebdf5fdfdf37eb3ff6bbd1d403bef4901a1fe88e81deec9d8bdaac7bf977d97b14cfcef1481ea0d0c13df599fb9383c914f6fbffbef64b3d66e6f6c5e555819c3efe5cb377354c03fef4dea9b6cf08f2da2376fedcbe8193b6299fe76772dbe595c03ad88d8a14f8479e03f046819435f8f3324f4e31faa201d54a045b39b47583b1ef8776d7bf889baac145628579ebcdcbff56d464ab7b2ce66d0cdeead54a9eecf566aa03606b00d2d00e7272febdf17a92b0aed6f37e3ba9afb7f98a446fcd0687fa1544f35f9c50ac46fedfd1dfbb6ce0ebb771a52d5ae7550077ffbcc6ffaded6b7e3bbb075cd5359f28f47bde6b3e822f3d96289b40a6e45760eace7a60343fd0cd0ea7add6e5d9ec517fa3dfff14caa40ecb6635d87e236c9997a06a7c7b6e88a235d1d288ab28edbbadb9f0314f736e8f2efc0e0ea7dadb3476c0f1a614506840df36b623483bcf73933ed0ee7a62bbe02f7bf53b4021e67b2caa3955ff1bad2911dbeb3b355fb7eaac512f8338303f8d67ef773c0672296f3a5f25052e0e81a38273e5c655a88af281a039301493bd4dd1e2ca0fb9a893776a86a6e7ae4fe5eb5146b4aaf96c8012f85993f18b3ea6eb7edf3137de49c1d0f37edc00a6fedddc5605945dcfcdfb514ca3bcef8bc47e88e75c6289f93dff0e96f6da98476f2d1e88df039e1d4f477fe6e4dde1748dc1eccfeeeff9b2fd8fa8f6ce69d81edbb724aca8c978cfb996ffd47422fd61d2b73ddcea6b9474ad4ff79eadf94700e5eea85307a012f9011b147545fc501000f4a0ef93315028d4cfdc520707bd9547fdd1d9c7fd9fec119b83a9ff2bfb4a47973e3baa80f01d57f1dab5cebbb6aaf2a8c1e289147f2cf7b25ced6679ba3a96834404fa3bf7c4750c01d9304651b35bd53bdc6fcab5478e336ff2e51ee7e6f629f6facbde2a10f83d3ef8a39e83b557cd4d97f7fdd5007637791466d50afcc9e7bf5cac6554ddf76490bb3f3d59ad7877df7ef29effb9b93aae7637f6bbcb7caef077e900cef54b9cbbaa393aa045ed1dfb9ba3ab146faab9cf4503ba1942fb3fb2d52a75428938a6fb99f5fffef7ba3b53e1db9af2ff740bc1e76737fbf9d9b96aafa9b266714ff4fc44cff589db07971b4d55df7f9477932ef55997ffa982328807542896abe4ca81be5111493bb747796b6a4462fd80754b533823f633f512aa52e85e6660ed54b2ed69a9ac6028ff1328f6688f432811dd997daa3e23e4ed9547940e976ef6aaf324fff24c93ea076ad4e01d1dfaf6abe2b83ce7ee7ef80c31e3fff3dc1dc5423b0d29be998a3544dffa688c3a890337bc0f2f6f235be99f1dfbe22a99ebe93ca6678743c5da00b87cf74773fcf0306f9bffd516c914ea8a5f714b3790337798a477355dfecb3725ef2d5762dff7a016dba71e66df7f8a0743b53ef64dbcb14e31be5772b593f6864f23ab7a23772550abd7bf51caaa59014b9d93dbfb3f7ef77bec8a72f875bda3b939db47dfd519ee7bf9d94767deeffb2503d1501c92d94476edf68ec743ab446bce66b019c335d11fb2555807208967ffdf666f276efbf7ca4db835d993e07347bf1dd1d592ec54d3527d5c6e2b11baf7a933eaf6d66658ae4bb9b54a999b667475b677243d0e06c14bfe4516629b6d8d511aabeda3ae550a6f341a17e86db32abbadf1a119546f26fa74468aa08f47b70fbe162a993714a9b414def9773f77928f228117147f9c98adcfb5401deb77f1be819cb73396017ddffe5fd00aff8a3cafbb6a8c6810d46450a7234a6373ca53d6e3e277cdfd47b25d8ab9b3733229f2852a40fcea99e5207647bdc035954e4aabbc1da9b3281c052fa667aeef942b1e5b0f485ffbb9faab76b547554a99d80677db3d7954dc1db9ee993f6977393db075319f64b754fb8dd2f806fdb1a0ce175b9e8a14e6645023f14a953efdf7375401d9b7270f416fc77b6daa37324df722baaf92d93f075ef8f3e3c5242ec3a55fea9f41d5da535bd6f3779651d7e43f0ca2467f6fa760f36ea8680ef9ae88f97672ed008ffdb7c940dd51ab4fb45f32d493157e4df1a7bf01a8a26f14714337f555b8dcdecbedf40378df9ef18a608e3b11fca6caa6defa818f73ead4780e4bcfb16064fd15c039e52a25bb2a8fea846225edb476075803de52f851d493807ea8a23fe7e0ee51ea7dadfdafeca0695004c388fc523a53965940dc52a5bce5536d11bfe983a920f432841d6c1e62057b1c501000f4a46ff8dedec57823a9ed9c0420c9f163dbedf551f9d4fd57cfa9f4dd1df6a85222cf299ed0c9ce56c74a362a116d1eb6234a3bdbbf920eac6d5dd3516b75408f9700e4daa2ab55de08ca72e7e6733bf7b6a8eff6db55a9ff5acaa22896eddbe1e601cbcd55eb3fa79e54e4be9264dbfcbd52d5b37694c1eaadad86505778c0170515f0ea733d7d477804c79208e79ef6a2e776c1e8f3fefd112d51f502337f6955aafba22e8671dbbe6a5aab7b6cb76df29da0c23ec529d4cc9b01a0838d532cb1a53b474aea9aa956af7e3d1d73c2334ccd00882f01bbaba8b3d170525b5bf7db52d4aa79e1d0670aad337aabf1b67db914888dd85dbb4461e19ff6f96ee88b5af4b8a3931b1e64be51fd940e49a0d07ebdd3caa4f7ec937fcbb54a8dec03be6bd967ee4caa74026220ed4fbd823a8ba3b96d117f3715ff96ff37def651da8f1efffb17d3bec0526551201db9547f2eee29ce976669f9efcfddb7d64aa791ae4d4bb700ee8ed147ffdd977d2e0f78d29cb55c62c1e7bea6a8e79af28977ef7553637ebbcd53d1d814bfe6d9bcd1ec97a3de864f8a043563cce5ccf0ee37b6f726fb135e718fe1f7de7f2666b6a877ef592288db4a15b593f77acd3ce7de65aa73f2dcc6b9e9c2e8a4763a513fb300d01b0ca3a7f147c7bcc514181fb57d1edb33bb075ef739a9fa79eb7cc44fffdcb2db92550a724b8072b5db0bba19c44cde356ffcaba0719fe01eb1467e8e940eaee28aa54834106f7edcddf01b1d88fcfa98a73d2f879b9aa95f36dfcf4f81dd73e5ad7a6df7281c6e6edfc6a0eef94d5023f201cf50687fa43725b6e457ed9fadfec6b958f29ee6ef66ff002626e4e632224e71afa8f59765538d4f8303f9d80d0813ce6f077f9bc995a6879fffc0ba86ffcbdbb3c7e0d4b536f8bafef3b33f3d3ecc5326d958b80766594021f19318f569af6fed61a039cd9d823372fbd871e00fe4f7b9b9b60f0743cc6e8f3f78de5972b7997c7e6acbe807630cdd97ea7740bd6ad9799ea3b9c8e901180bc9707bd50a154b33fc6d5aafe7a46ab57b8e93b649fea8bffd5714d11e5cb93046aa67bb7f309222ea8bdbf11bf7ea54975efe7046b3daa14df01ab37964a19be094ff4462db3db14e0f155eb73a07afff9a3c1df032ffd8af54d64472fc6b40ecf29532cc96d5055478193a75b11a794dcde0e8742377ca26d85fdb56512804437ae4f58c5e65f81ae6ec8c7eeabbdfa8c03d69c839ebfed57cd53079e65af5bf9e6d2aca948eed3cf33bcf7bb54c8a7f25ed57e1dfa27510773f044f79a0097c0de511f7bce4e08b6c8a64cfc2f534794758abffcd3cd354d6c79f03f99914c9d55b7bd5006f8b2a9d0ca0a8ebc0715cbef7e4bddcc1180b28acb2dff2daa0029e6a8e4b363724cbd0383bcf8ef713abcf6c03f941a10283cc2fd9b7d6e363c6f3725cdc501000f4aacca9b7fbfc0cddc7b64664aa27e5523d53b6c8d7b0753324fc5e5f1b7b86df2bfb59bcbd9f654376e68efca77ca07779558342fd0bfb7446fff9783d112f8763abddaaa818f66e7a591cf3b6681ebe03a3a9ef49c8a3d760f1b51fdf73f9efdd5321f8afdffe667bfb8d7bfefd051a81dde49b66502e0483384fff8cd6e28bd1dc9aaef143223d53fa5db2e4bb002a1355ecdac819512decbf9fac289fd1e26fc11eb9f0dbbfaad408d249f83acabdfea8d11b9bb660ee400982b6ffc3c6e4f77678095d967f157144513ff9dea90087251db55d9efdc53d6383bf51d71bb65b754665be3f0bcdbb67fea54403966ca3a57b354677fb35556ee01da19c0e625f67ed4d9732fefaeb7d8b01d51eb203400913eaaa596fffec1e8eff27aff9d1e700cdfdea8fe64ee7cf3e1ed93aa3d446de29db91462af56157ee732a8947bb63df5b6cbe35bbb83d579ef5b14c1d36a3cd5d937c0d08143cdddc1eb72fe5513bb767fdb9fef5435c9d954be0d5d1d720303f95ae8eecfdb38dd9bcdb664a2308c0110d24dbc9c57b3cc6794ee291e6dde6de295be71aa0ef39fb9f1e4baa6fdabfcfddb933475bfc64ba1f7b98ec78b3007ecf499bd503be6fb3f67046b00b064ebd807ac5bf6cf6fe6eb3f823d037477714cb0ec3a899140e9beee6b3681c83a5fbfc518ddaa43277034d65b6d50a52ef79b4beaab7bfffa77db0025f5560ee01dc032ddad67fc3ad052cfe677d7760344fcd1f1d67d4fe4d99ed9e1e5dd50de667339c6e88d5cf83efbbcf819bb231aa217f3d915c5176dc1d653eedc955c56d88d3dec69b512583dce457143528153eef9b2e9751d8eeea8fa9eb6ce64d9e9cf0eef69f83ef1a6a88f73f0badac4036b0e874a78a144555ededf3397a3cb3446aa2e29cc57d9ee3554cc00b82972efb4baea8be83bbd53f9e935429b54f2a892ec51b0fbda6c92fa8898a7c237c7aac470393c05779ee818becae7ebde4b18b446dfcc8d33ee4ccefb31837fed7ffacdcb651dcb5b51f6d4d1dc1da88230183af20a7051d032c4dbb94441e28fecfb32f54debdf942d3fe8071abd51cb1a917a3bb724dfcdaa9ef58cf2c6ff72cf6298d664ee0eeb7ebee6d0687f5ef7f11aff5481c53abc56c4f0f075aa2dfda3bcc3b0d62a1dfbc2397ff36f19ffa37ebd113f9e99aa14f6fdf09992291d4fed956ed1d64f64b822f955daa0bfde1d6ba200cdce08dc83a99ef7ee5f637fc1e6e3347bbfae816e4bfdd5395a9074a36ff64fccfc54a59efbd2dfd0cdf7cc8ebfb900c8ebd3eaadf7773e9f446f4578014f76bb66cac667acf499d498a681ddf2aff2ee7a7cfc130a3df110bc46cacf97b9b276d6f7f32d740e08fbdfc1d4cf088a87755455444bc9e515acf8e81a17e7f518d796ada9e7ed0353634a6df65fcb681bdba193d7bdf51e5c0ff5481be72ac501000f");
    //        RTMPConnection conn = RTMPConnManager.getInstance().createConnection(RTMPMinaConnection.class);
    //        conn.setStateCode(RTMP.STATE_CONNECTED);
    //
    //        RTMPClientProtocolDecoder decoder = new RTMPClientProtocolDecoder();
    //        decoder.decodeBuffer(conn, IoBuffer.wrap(buf));
    //    }

    @Test
    public void testDecodeBufferYugeTimestamp2() {
        // this test data is from a flash forum
        byte[] buf = IOUtils.hexStringToByteArray(
                "06FFFFFF00BC48090100000001643DD012 00 00 84 02 80 9E FE 25 2A 2E F8 30 3F 99 FC 4C AB 6E B7 2F CB CB E2 DE 62 A7 86 9E BC 3E 04 05 73 C3 D1 F0 FB 70 45 12 80 32 CE 4C B3 44 6E 76 37 66 62 C7 A5 84 A0 60 12 C1 80 51 55 E9 41 80 D5 06 01 69 55 53 EB 70 18 06 10 60 1A FC C2 BF 4C B0 03 65 1D E5 FA 6B 57 AE 91 80 C0 25 03 00 A9 E2 EB F0 60 36 01 80 61 B6 65 1D 83 00 D0 0C 03 82 B1 16 C0 52 89 35 7A D3 18 C2 88 F6 CD C601 64 3D D000 FE 03 02 F7 28 19 50 AF F8 55 7D 8C 35 1A 33 0E 3D EE DC F2 75 3B 10 E4 CC 21 7B CF C1 80 11 06 00 64 BF 41 0C 18 00 F0 0C F9 7F F2 DA AC 18 01 10 40 56 3B C5 17 3F 4B BE AD AC B6 E5 DC C1 DC 91 EF 60 C0 4F 03 00 3E 5F B6 83 00 36 0C 00 99 7C A0 C1 88 80 62 A5 3C C0 60 9E 81 80 10 CD 9B 59 83 BE B0 E8 F5 40 C0 22 03 00 A9 00 D8 30 0C 00 C0 36 97 F2 D0 60 5E 02 1D 08 39 F1 EE 03C6 01 64 3D D0 04 F2 07 FB 67 07 6D F6 5B B6 9F 8E A0 C0 20 83 00 B3 E5 14 18 0F 00 84 AF 6A AD A2 58 30 02 A0 C0 0C 04 39 F8 A6 A9 D0 60 3E 41 80 10 2F 6B 16 98 C0 8A E6 AF 2A 12 44 75 73 CA F6 62 90 3F FA 58 08 52 6C 63 AB 64 E1 D8 70 43 FC 9C 2F 2F F5 9B 66 5A 0A AD FB 48 0C 3D E8 18 0A 60 60 09 2D 55 FF 03 00 3A 0C 00 CA 8B C5 70 4B 06 00 60 18 00 F2 FA 8F EA 84 88 5D F9 19 6B E0 59 EF 5E 06");
        RTMPMinaConnection conn = (RTMPMinaConnection) RTMPClientConnManager.getInstance().createConnection(RTMPMinaConnection.class);
        conn.setStateCode(RTMP.STATE_CONNECTED);

        RTMPClientProtocolDecoder decoder = new RTMPClientProtocolDecoder();
        decoder.decodeBuffer(conn, IoBuffer.wrap(buf));
    }

    @Test
    public void testDecodeBufferExTS() {
        //log.debug("\testDecodeBufferExTS");
        RTMPProtocolDecoder dec = new RTMPProtocolDecoder();
        RTMPConnection conn = new RTMPMinaConnection();
        Red5.setConnectionLocal(conn);
        RTMP rtmp = conn.getState();
        rtmp.setState(RTMP.STATE_CONNECTED);
        // lastHeader: Header [streamId=1, channelId=4, dataType=18, timerBase=0, timerDelta=0, size=309, extended=false]
        // get the local decode state
        RTMPDecodeState state = conn.getDecoderState();
        // meta and audio 4x chunks
        IoBuffer in = IoBuffer.wrap(IOUtils.hexStringToByteArray(
                "04000000000135120100000002000d40736574446174614672616d6502000a6f6e4d65746144617461080000000d00086475726174696f6e0040d518000000000000057769647468004064000000000000000668656967687400405e000000000000000d766964656f64617461726174650040686a000000000000096672616d657261746500403900000000c40000000c766964656f636f6465636964004000000000000000000d617564696f6461746172617465000000000000000000000f617564696f73616d706c65726174650040d5888000000000000f617564696f73616d706c6573697a65004030000000000000000673746572656f0100000c617564696f636f6465636964004000c40000000000000007656e636f64657202000d4c61766635362e31352e313032000866696c6573697a650000000000000000000000090400000000006908010000002afff340c400104002e62d4110009080200830107ea04cfa810710e0987f820ec130fc401897c1c0c70ff502008020eea04c1f0fe7fcb9fc10ff90d107c1f82008021feb07c1c04010041c20f89c1fff6b6edad93d99d8da6cd42a08e459095589d4b5fb9a4e679a1f4400001a00006a082afff342c41a19c91f225d89300055a47640c62cee7ccc85c08c42cadb6b56daebe65989f78c3ef3cfbd694ac0c34aa855ee0598a031f0a0686212d43631a4c59a926383c2d5201c5e9b7377"));
        Packet packet = null;
        do {
            packet = dec.decodePacket(conn, state, in);
        } while (packet == null);
        assertNotNull(packet);
        assertTrue(packet.getMessage() instanceof Notify);
        do {
            packet = dec.decodePacket(conn, state, in);
        } while (packet == null);
        assertNotNull(packet);
        assertTrue(packet.getMessage() instanceof AudioData);
    }

    @Test
    public void testExtendedTImestampPartialPacket() {
        //Buffer contains 2 complete objects and 1 incomplete object.
        byte[] buf = IOUtils.hexStringToByteArray("03ffffff00004b090100000005584fce270100002800000042419e1e45152c236f0000030000030000030000030000030000030000030000030000030000030000030000030000030000030000030000030000030000030000049c03ffffff000008080100000005584fd1af01211004608c1c03ffffff000049090100000005");
        RTMPMinaConnection conn = (RTMPMinaConnection) RTMPClientConnManager.getInstance().createConnection(RTMPMinaConnection.class);
        conn.setStateCode(RTMP.STATE_CONNECTED);
        RTMPClientProtocolDecoder decoder = new RTMPClientProtocolDecoder();
        List<Object> objects = decoder.decodeBuffer(conn, IoBuffer.wrap(buf));
        //RTMPDecodeState state = conn.getDecoderState();
        assertTrue(objects.size() == 2);

    }
}

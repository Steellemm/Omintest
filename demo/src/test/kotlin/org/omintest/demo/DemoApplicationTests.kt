package org.omintest.demo

import org.omintest.omintestextension.engine.Omintest
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@OmintContextConfiguration
@Omintest(["uptest.json"])
class DemoApplicationTests {
}

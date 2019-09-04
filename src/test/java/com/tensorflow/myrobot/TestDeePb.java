package com.tensorflow.myrobot;

import org.apache.commons.io.IOUtils;
import org.tensorflow.Graph;
import org.tensorflow.Session;
import org.tensorflow.Tensor;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description:
 * @Author: panda
 * @Date: 2019/4/29 9:19
 */

public class TestDeePb {

    public void test() throws IOException {

        try(Graph graph = new Graph()){
            byte[] graphBytes = IOUtils.toByteArray(
                    new FileInputStream("D:/work/ideawork/myrobot/src/main/resources/static/DeepQAmodel.pb"));
            graph.importGraphDef(graphBytes);

            try(Session session = new Session(graph)){
                Tensor<?> out = session.runner()
                        .feed("X",Tensor.create(""))
                        .fetch("results").run().get(0);
                float[] r = new float[1];
                out.copyTo(r);
                System.out.println(r[0]);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new TestDeePb().test();
    }
}

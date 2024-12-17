package io.broadcast.wrapper.jeds;

import io.broadcast.engine.AbstractBroadcastPipelineWrapper;
import io.broadcast.engine.announcement.StringAnnouncement;
import io.broadcast.wrapper.jeds.dispatcher.JedisDispatcher;
import io.broadcast.wrapper.jeds.extractor.JedisRecordExtractor;

public class JedisBroadcastPipeline
        extends AbstractBroadcastPipelineWrapper<String, StringAnnouncement, JedisBroadcastPipeline> {

    public static JedisBroadcastPipeline createPipeline() {
        return new JedisBroadcastPipeline();
    }

    public JedisBroadcastPipeline setDispatcher(JedisDispatcher jedisDispatcher) {
        internalPipe.setDispatcher(jedisDispatcher);
        return this;
    }

    public JedisBroadcastPipeline setRecordExtractor(JedisRecordExtractor recordExtractor) {
        internalPipe.setRecordExtractor(recordExtractor);
        return this;
    }
}

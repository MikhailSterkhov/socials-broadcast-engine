package io.broadcast.wrapper.telegram;

import com.pengrad.telegrambot.TelegramBot;
import io.broadcast.engine.BroadcastPipeline;
import io.broadcast.engine.announcement.AnnouncementExtractor;
import io.broadcast.engine.dispatch.BroadcastDispatcher;
import io.broadcast.engine.dispatch.ComplexBroadcastDispatcher;
import io.broadcast.engine.event.BroadcastListener;
import io.broadcast.engine.record.extract.RecordExtractor;
import io.broadcast.engine.scheduler.Scheduler;
import io.broadcast.wrapper.telegram.objects.TelegramMessage;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class TelegramBroadcastPipeline implements BroadcastPipeline<Long, TelegramMessage> {
    
    private final BroadcastPipeline<Long, TelegramMessage> internalPipe = BroadcastPipeline.createPipeline();
    private final Set<BroadcastDispatcher<Long, TelegramMessage>> dispatchers = new HashSet<>();

    public TelegramBroadcastPipeline(@NotNull String apiToken) {
        this(new TelegramBot(apiToken));
    }

    public TelegramBroadcastPipeline(@NotNull TelegramBot.Builder builder) {
        this(builder.build());
    }

    public TelegramBroadcastPipeline(@NotNull TelegramBot telegramBot) {
        this.dispatchers.add(new TelegramBotDispatcher(telegramBot));
    }
    
    @Override
    public TelegramBroadcastPipeline setAnnouncementExtractor(AnnouncementExtractor<TelegramMessage> announcementExtractor) {
        internalPipe.setAnnouncementExtractor(announcementExtractor);
        return this;
    }

    @Override
    public TelegramBroadcastPipeline setDispatcher(BroadcastDispatcher<Long, TelegramMessage> dispatcher) {
        dispatchers.add(dispatcher);
        return this;
    }

    @Override
    public TelegramBroadcastPipeline setRecordExtractor(RecordExtractor<Long> recordsExtractor) {
        internalPipe.setRecordExtractor(recordsExtractor);
        return this;
    }

    @Override
    public TelegramBroadcastPipeline setScheduler(Scheduler scheduler) {
        internalPipe.setScheduler(scheduler);
        return this;
    }

    @Override
    public TelegramBroadcastPipeline addListener(BroadcastListener listener) {
        internalPipe.addListener(listener);
        return this;
    }

    @Override
    public AnnouncementExtractor<TelegramMessage> getAnnouncementExtractor() {
        return internalPipe.getAnnouncementExtractor();
    }

    @Override
    public BroadcastDispatcher<Long, ?> getDispatcher() {
        return ComplexBroadcastDispatcher.complex(dispatchers);
    }

    @Override
    public RecordExtractor<Long> getRecordExtractor() {
        return internalPipe.getRecordExtractor();
    }

    @Override
    public Scheduler getScheduler() {
        return internalPipe.getScheduler();
    }

    @Override
    public Iterable<BroadcastListener> getListeners() {
        return internalPipe.getListeners();
    }
}
